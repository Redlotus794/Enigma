package com.rdlts.enigma.demo.userinterface.api;

import com.rdlts.enigma.ddd.core.exception.DomainEntityNotFoundException;
import com.rdlts.enigma.demo.application.DemoTaskApplicationService;
import com.rdlts.enigma.demo.domain.DemoTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * DemoTask 示例接口层控制器。
 *
 * @author wangjialong
 * @since 2026/05/17 10:00
 */
@RestController
@RequestMapping(path = "/api/v1/demo-tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoTaskController {

    private final DemoTaskApplicationService demoTaskApplicationService;

    /**
     * 创建控制器。
     *
     * @param demoTaskApplicationService 任务应用服务
     */
    public DemoTaskController(DemoTaskApplicationService demoTaskApplicationService) {
        this.demoTaskApplicationService = demoTaskApplicationService;
    }

    /**
     * 查询全部示例任务。
     *
     * @return 统一响应体
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<DemoTaskResponse>>> list() {
        List<DemoTaskResponse> responseList = new ArrayList<>();
        for (DemoTask demoTask : demoTaskApplicationService.list()) {
            responseList.add(toResponse(demoTask));
        }
        return ResponseEntity.ok(ApiResponse.success(responseList));
    }

    /**
     * 查询单个示例任务。
     *
     * @param id 任务主键
     * @return 统一响应体
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DemoTaskResponse>> get(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(toResponse(demoTaskApplicationService.get(id))));
        } catch (DomainEntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("NOT_FOUND", exception.getMessage(), null));
        }
    }

    /**
     * 创建示例任务。
     *
     * @param request 创建请求
     * @return 统一响应体
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponse<DemoTaskResponse>> create(@RequestBody(required = false) CreateDemoTaskRequest request) {
        if (request == null || !StringUtils.hasText(request.name)) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.failure("BAD_REQUEST", "demo task name must not be blank", null));
        }
        DemoTask demoTask = demoTaskApplicationService.create(request.name);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(toResponse(demoTask)));
    }

    /**
     * 删除示例任务。
     *
     * @param id 任务主键
     * @return 统一响应体
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") String id) {
        try {
            demoTaskApplicationService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (DomainEntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.failure("NOT_FOUND", exception.getMessage(), null));
        }
    }

    @Nonnull
    private DemoTaskResponse toResponse(@Nonnull DemoTask demoTask) {
        return new DemoTaskResponse(demoTask.identity(), demoTask.name(), demoTask.createdAt().toString());
    }

    /**
     * 创建任务请求体。
     *
     * @author wangjialong
     * @since 2026/05/17 10:00
     */
    public static final class CreateDemoTaskRequest {

        public String name;

        public CreateDemoTaskRequest() {
        }
    }

    /**
     * 任务响应体。
     *
     * @author wangjialong
     * @since 2026/05/17 10:00
     */
    public static final class DemoTaskResponse {

        public final String id;
        public final String name;
        public final String createdAt;

        public DemoTaskResponse(String id, String name, String createdAt) {
            this.id = id;
            this.name = name;
            this.createdAt = createdAt;
        }
    }

    /**
     * 统一响应结构。
     *
     * @param <T> 数据类型
     * @author wangjialong
     * @since 2026/05/17 10:00
     */
    public static final class ApiResponse<T> {

        public final String code;
        public final String message;
        public final T data;

        private ApiResponse(String code, String message, @Nullable T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        @Nonnull
        public static <T> ApiResponse<T> success(@Nullable T data) {
            return new ApiResponse<>("SUCCESS", "success", data);
        }

        @Nonnull
        public static <T> ApiResponse<T> failure(@Nonnull String code, @Nonnull String message, @Nullable T data) {
            return new ApiResponse<>(code, message, data);
        }
    }
}


