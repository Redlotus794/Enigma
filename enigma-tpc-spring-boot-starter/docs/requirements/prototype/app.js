const requests = [
    {
        id: "TPC202605290001",
        type: "VehicleProfileSync",
        community: "VehicleCloudCommunity",
        sender: "VehicleCloudSender",
        status: "WAIT",
        bizSuccess: null,
        executions: 0,
        requestTime: "2026-05-29 09:18:22",
        lastExecutionTime: "-",
        payloadClass: "VehicleProfilePayload",
        notes: "等待首次同步",
        payload: { vin: "LFPH4ABC9P1A00001", market: "CN", source: "vehicle-center" }
    },
    {
        id: "TPC202605290002",
        type: "TboxCommandPush",
        community: "TelematicsCommunity",
        sender: "TboxCommandSender",
        status: "STAND_BY",
        bizSuccess: null,
        executions: 1,
        requestTime: "2026-05-29 09:36:08",
        lastExecutionTime: "2026-05-29 14:20:31",
        payloadClass: "TboxCommandPayload",
        notes: "第三方调用中",
        payload: { vin: "LFPH4ABC9P1A00002", command: "REFRESH_STATUS", timeout: 30000 }
    },
    {
        id: "TPC202605290003",
        type: "OwnerMessagePush",
        community: "MessagePushCommunity",
        sender: "AppMessageSender",
        status: "COMPLETED",
        bizSuccess: true,
        executions: 1,
        requestTime: "2026-05-29 10:04:44",
        lastExecutionTime: "2026-05-29 10:05:01",
        payloadClass: "MessagePushPayload",
        notes: "业务响应成功",
        payload: { userId: "U10029", template: "MAINTENANCE_NOTICE", channel: "APP" }
    },
    {
        id: "TPC202605290004",
        type: "VehicleProfileSync",
        community: "VehicleCloudCommunity",
        sender: "VehicleCloudSender",
        status: "ERROR",
        bizSuccess: false,
        executions: 3,
        requestTime: "2026-05-29 10:30:19",
        lastExecutionTime: "2026-05-29 13:42:16",
        payloadClass: "VehicleProfilePayload",
        notes: "Sender Time out",
        payload: { vin: "LFPH4ABC9P1A00004", market: "EU", source: "vehicle-center" }
    },
    {
        id: "TPC202605290005",
        type: "WarrantyClaimSubmit",
        community: "DealerNetworkCommunity",
        sender: "DealerWarrantySender",
        status: "RECEIVED",
        bizSuccess: null,
        executions: 2,
        requestTime: "2026-05-29 11:22:45",
        lastExecutionTime: "2026-05-29 14:12:07",
        payloadClass: "WarrantyClaimPayload",
        notes: "等待回调处理",
        payload: { claimNo: "WC-20260529-007", dealerCode: "DLR-0218", amount: 1280.5 }
    },
    {
        id: "TPC202605290006",
        type: "OwnerMessagePush",
        community: "MessagePushCommunity",
        sender: "SmsMessageSender",
        status: "WAIT",
        bizSuccess: null,
        executions: 0,
        requestTime: "2026-05-29 12:08:03",
        lastExecutionTime: "-",
        payloadClass: "MessagePushPayload",
        notes: "等待批量发运",
        payload: { userId: "U10077", template: "RECALL_NOTICE", channel: "SMS" }
    }
];

const deliveryResults = [
    {
        id: "TPCDR202605290001",
        requestId: "TPC202605290003",
        community: "MessagePushCommunity",
        sender: "AppMessageSender",
        success: true,
        code: "200",
        step: "-"
    },
    {
        id: "TPCDR202605290002",
        requestId: "TPC202605290004",
        community: "VehicleCloudCommunity",
        sender: "VehicleCloudSender",
        success: false,
        code: "1002",
        step: "STAND_BY"
    },
    {
        id: "TPCDR202605290003",
        requestId: "TPC202605290005",
        community: "DealerNetworkCommunity",
        sender: "DealerWarrantySender",
        success: true,
        code: "200",
        step: "-"
    }
];

const registries = {
    requestTypes: [
        ["VehicleProfileSync", "路由至 VehicleCloudCommunity"],
        ["TboxCommandPush", "路由至 TelematicsCommunity"],
        ["OwnerMessagePush", "路由至 MessagePushCommunity"],
        ["WarrantyClaimSubmit", "路由至 DealerNetworkCommunity"]
    ],
    communities: [
        ["VehicleCloudCommunity", "车辆云服务社群，可生成 VIN 同步计划"],
        ["TelematicsCommunity", "车联网指令社群，按命令类型选择发送器"],
        ["MessagePushCommunity", "消息推送社群，支持 APP / SMS 发送器"]
    ],
    senders: [
        ["VehicleCloudSender", "超时 30000 ms，处理车辆资料同步"],
        ["TboxCommandSender", "超时 45000 ms，处理 TBOX 指令下发"],
        ["AppMessageSender", "超时 15000 ms，处理 APP 站内信"],
        ["SmsMessageSender", "超时 15000 ms，处理短信推送"]
    ]
};

let activeStatusFilter = "ALL";
let selectedRequestIds = new Set();

const statusLabels = ["ALL", "WAIT", "STAND_BY", "RECEIVED", "COMPLETED", "ERROR"];

function statusBadge(status) {
    return `<span class="status-badge status-${status}">${status}</span>`;
}

function successText(value) {
    if (value === true) {
        return "true";
    }
    if (value === false) {
        return "false";
    }
    return "-";
}

function filteredRequests() {
    const keyword = document.getElementById("searchInput").value.trim().toLowerCase();
    return requests.filter((request) => {
        const matchStatus = activeStatusFilter === "ALL" || request.status === activeStatusFilter;
        const matchKeyword = !keyword
            || request.id.toLowerCase().includes(keyword)
            || request.type.toLowerCase().includes(keyword)
            || request.community.toLowerCase().includes(keyword)
            || request.sender.toLowerCase().includes(keyword);
        return matchStatus && matchKeyword;
    });
}

function renderStats() {
    const counts = requests.reduce((result, request) => {
        result[request.status] = (result[request.status] || 0) + 1;
        return result;
    }, {});
    document.getElementById("waitCount").textContent = counts.WAIT || 0;
    document.getElementById("progressCount").textContent = (counts.STAND_BY || 0) + (counts.RECEIVED || 0);
    document.getElementById("completedCount").textContent = counts.COMPLETED || 0;
    document.getElementById("errorCount").textContent = counts.ERROR || 0;
}

function renderDashboardTable() {
    const rows = filteredRequests().slice(0, 6).map((request) => `
        <tr>
            <td><strong>${request.id}</strong></td>
            <td>${request.type}</td>
            <td>${request.community}<br><span class="muted">${request.sender}</span></td>
            <td>${statusBadge(request.status)}</td>
            <td>${request.executions}</td>
            <td>${request.lastExecutionTime}</td>
            <td>
                <div class="row-actions">
                    <button class="text-button" data-open-request="${request.id}" type="button">详情</button>
                </div>
            </td>
        </tr>
    `).join("");
    document.getElementById("dashboardTable").innerHTML = rows || emptyRow(7);
}

function renderRequestTabs() {
    document.getElementById("requestStatusTabs").innerHTML = statusLabels.map((status) => `
        <button class="segment ${activeStatusFilter === status ? "active" : ""}" data-filter-status="${status}" type="button">${status}</button>
    `).join("");
}

function renderRequestTable() {
    const rows = filteredRequests().map((request) => `
        <tr>
            <td><input class="request-checkbox" type="checkbox" data-request-id="${request.id}" ${selectedRequestIds.has(request.id) ? "checked" : ""} aria-label="选择 ${request.id}"></td>
            <td><strong>${request.id}</strong></td>
            <td>${request.type}</td>
            <td>${statusBadge(request.status)}</td>
            <td>${successText(request.bizSuccess)}</td>
            <td>${request.executions}</td>
            <td>${request.requestTime}</td>
            <td>
                <div class="row-actions">
                    <button class="text-button" data-open-request="${request.id}" type="button">详情</button>
                </div>
            </td>
        </tr>
    `).join("");
    document.getElementById("requestTable").innerHTML = rows || emptyRow(8);
    updateSelectionHint();
}

function renderErrorActivity() {
    const errors = requests.filter((request) => request.status === "ERROR");
    document.getElementById("errorActivity").innerHTML = errors.map((request) => `
        <div class="activity-item">
            <strong>${request.id}</strong>
            <span>${request.type} · ${request.notes} · ${request.lastExecutionTime}</span>
        </div>
    `).join("") || `<div class="activity-item"><strong>暂无异常</strong><span>当前没有 ERROR 请求</span></div>`;
}

function renderDelivery() {
    document.getElementById("planList").innerHTML = [
        ["TPCDP-001", "VehicleCloudCommunity", "3 个请求", "timeout 30000 ms"],
        ["TPCDP-002", "MessagePushCommunity", "5 个请求", "遇错中断"],
        ["TPCDP-003", "TelematicsCommunity", "2 个请求", "顺序执行"]
    ].map((plan) => `
        <div class="plan-item">
            <strong>${plan[0]} · ${plan[1]}</strong>
            <span>${plan[2]} · ${plan[3]}</span>
        </div>
    `).join("");

    document.getElementById("resultTable").innerHTML = deliveryResults.map((result) => `
        <tr>
            <td><strong>${result.id}</strong></td>
            <td>${result.requestId}</td>
            <td>${result.community}</td>
            <td>${result.sender}</td>
            <td>${result.success ? "success" : "failed"}</td>
            <td>${result.code}</td>
            <td>${result.step}</td>
        </tr>
    `).join("");
}

function renderRegistries() {
    renderRegistryList("requestTypeList", registries.requestTypes);
    renderRegistryList("communityList", registries.communities);
    renderRegistryList("senderList", registries.senders);
}

function renderRegistryList(targetId, items) {
    document.getElementById(targetId).innerHTML = items.map((item) => `
        <div class="registry-item">
            <strong>${item[0]}</strong>
            <span>${item[1]}</span>
        </div>
    `).join("");
}

function emptyRow(colspan) {
    return `<tr><td colspan="${colspan}">没有匹配的数据</td></tr>`;
}

function renderAll() {
    renderStats();
    renderDashboardTable();
    renderRequestTabs();
    renderRequestTable();
    renderErrorActivity();
    renderDelivery();
    renderRegistries();
}

function switchView(viewName) {
    document.querySelectorAll(".view").forEach((view) => view.classList.remove("active"));
    document.querySelectorAll(".nav-item").forEach((item) => item.classList.remove("active"));
    const view = document.getElementById(`${viewName}View`);
    view.classList.add("active");
    document.querySelector(`[data-view="${viewName}"]`).classList.add("active");
    document.getElementById("viewTitle").textContent = view.dataset.title;
}

function setStatusFilter(status) {
    activeStatusFilter = status;
    document.querySelectorAll(".segment").forEach((button) => button.classList.toggle("active", button.dataset.filterStatus === status));
    renderDashboardTable();
    renderRequestTabs();
    renderRequestTable();
}

function openDrawer(requestId) {
    const request = requests.find((item) => item.id === requestId);
    if (!request) {
        return;
    }
    document.getElementById("drawerTitle").textContent = request.id;
    document.getElementById("drawerBody").innerHTML = `
        <div class="detail-list">
            ${detailRow("请求类型", request.type)}
            ${detailRow("状态", statusBadge(request.status))}
            ${detailRow("第三方社群", request.community)}
            ${detailRow("发送器", request.sender)}
            ${detailRow("业务成功", successText(request.bizSuccess))}
            ${detailRow("执行次数", request.executions)}
            ${detailRow("请求时间", request.requestTime)}
            ${detailRow("最后执行时间", request.lastExecutionTime)}
            ${detailRow("载荷类型", request.payloadClass)}
            ${detailRow("备注", request.notes)}
        </div>
        <pre class="payload-box">${JSON.stringify(request.payload, null, 2)}</pre>
    `;
    const drawer = document.getElementById("drawer");
    drawer.classList.add("open");
    drawer.setAttribute("aria-hidden", "false");
}

function detailRow(label, value) {
    return `<div class="detail-row"><span>${label}</span><strong>${value}</strong></div>`;
}

function closeDrawer() {
    const drawer = document.getElementById("drawer");
    drawer.classList.remove("open");
    drawer.setAttribute("aria-hidden", "true");
}

function updateSelectionHint() {
    document.getElementById("selectionHint").textContent = `已选择 ${selectedRequestIds.size} 个请求`;
}

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.textContent = message;
    toast.classList.add("show");
    window.setTimeout(() => toast.classList.remove("show"), 1800);
}

document.addEventListener("click", (event) => {
    const target = event.target instanceof Element ? event.target : event.target.parentElement;
    if (!target) {
        return;
    }
    const navButton = target.closest("[data-view]");
    const filterButton = target.closest("[data-filter-status]");
    const detailButton = target.closest("[data-open-request]");
    const bulkButton = target.closest("[data-bulk-action]");

    if (navButton) {
        switchView(navButton.dataset.view);
        return;
    }
    if (filterButton) {
        setStatusFilter(filterButton.dataset.filterStatus);
        return;
    }
    if (detailButton) {
        openDrawer(detailButton.dataset.openRequest);
        return;
    }
    if (bulkButton) {
        if (selectedRequestIds.size === 0) {
            showToast("请先选择请求");
            return;
        }
        showToast(`已提交 ${selectedRequestIds.size} 个请求的${bulkButton.textContent}`);
        return;
    }
});

document.getElementById("searchInput").addEventListener("input", () => {
    renderDashboardTable();
    renderRequestTable();
});

document.getElementById("closeDrawerButton").addEventListener("click", closeDrawer);

document.getElementById("refreshButton").addEventListener("click", () => {
    renderAll();
    showToast("数据已刷新");
});

document.getElementById("createRequestButton").addEventListener("click", () => {
    switchView("requests");
    showToast("原型中展示创建入口，表单由业务系统扩展");
});

document.getElementById("runPlanButton").addEventListener("click", () => {
    const community = document.getElementById("communitySelect").value;
    const parallel = document.getElementById("parallelInput").value;
    showToast(`${community} 发运计划已提交，并行数 ${parallel}`);
});

document.getElementById("copyConfigButton").addEventListener("click", () => {
    showToast("配置已复制到剪贴板原型事件");
});

document.getElementById("selectAllRequests").addEventListener("change", (event) => {
    selectedRequestIds = new Set(event.target.checked ? filteredRequests().map((request) => request.id) : []);
    renderRequestTable();
});

document.addEventListener("change", (event) => {
    const target = event.target instanceof Element ? event.target : event.target.parentElement;
    if (!target) {
        return;
    }
    const checkbox = target.closest(".request-checkbox");
    if (!checkbox) {
        return;
    }
    if (checkbox.checked) {
        selectedRequestIds.add(checkbox.dataset.requestId);
    } else {
        selectedRequestIds.delete(checkbox.dataset.requestId);
    }
    updateSelectionHint();
});

renderAll();
