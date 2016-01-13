package net.bytebuddy.implementation.attribute;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.test.utility.MockitoRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public abstract class AbstractAttributeAppenderTest {

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    protected TypeDescription instrumentedType;

    @Mock
    protected AnnotationValueFilter annotationValueFilter;

    @Before
    public void setUp() throws Exception {
        when(annotationValueFilter.isRelevant(any(AnnotationDescription.class), any(MethodDescription.InDefinedShape.class))).thenReturn(true);
    }

    @Retention(RetentionPolicy.SOURCE)
    protected @interface Qux {

        class Instance implements Qux {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Qux.class;
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    protected @interface Baz {

        class Instance implements Baz {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Baz.class;
            }
        }
    }

    @Retention(RetentionPolicy.CLASS)
    protected @interface QuxBaz {

        class Instance implements QuxBaz {

            @Override
            public Class<? extends Annotation> annotationType() {
                return QuxBaz.class;
            }
        }
    }
}
