import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("all")
@SpringBootApplication
@ComponentScan("com.course")
/**
 * @SpringBootApplication用来标注在主程序的，表明是一个springboot项目
 * @ComponentScan（包扫描）
 *
 * 程序主入口
 */
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
