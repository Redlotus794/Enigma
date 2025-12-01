package com.rdlts.enigma.ddd.core.support;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 *
 * <h1>ValueObjectProcessor</h1>
 * 在编译的过程中检测使用@ValueObject的对象是否使用了Setter方法（以set开头的），并警告。
 *
 * @see com.rdlts.enigma.ddd.core.ValueObject
 * @author wangjialong
 * @since 2025/11/28 14:44
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.rdlts.enigma.ddd.core.ValueObject")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ValueObjectProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                if (element.getKind() == ElementKind.CLASS) {
                    checkForSetters((TypeElement) element);
                }
            }
        }
        return true;
    }

    private void checkForSetters(TypeElement classElement) {
        for (Element enclosedElement : classElement.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) enclosedElement;
                String methodName = method.getSimpleName().toString();

                if (methodName.startsWith("set") && methodName.length() > 3) {
                    // 发出编译警告
                    processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.WARNING,
                            "ValueObject 类 [" + classElement.getSimpleName() +
                                    "] 包含 setter 方法 [" + methodName + "]，这违反了值对象不可变原则",
                            method
                    );
                }
            }
        }
    }
}
