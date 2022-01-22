import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：JDBC框架之update方法
 * Package(包名): PACKAGE_NAME
 * Class(测试类名): JDBCTemplateTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/22
 * Time(创建时间)： 21:00
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

class JDBCTemplateTest
{

    @org.junit.jupiter.api.Test
    void update()
    {
        String sql = "insert into information values(?,?,?,?)";
        Object[] objects = {23, "张3", "男", 21};
        int result = JDBCTemplate.update(sql, objects);
        System.out.println(result);
    }

    @org.junit.jupiter.api.Test
    void update1()
    {
        String sql = "update information set age=? where no=?";
        Object[] objects = {22, 23};
        int result = JDBCTemplate.update(sql, objects);
        System.out.println(result);
    }

    @org.junit.jupiter.api.Test
    void update2()
    {
        String sql = "delete from information where no=?";
        Object[] objects = {23};
        int result = JDBCTemplate.update(sql, objects);
        System.out.println(result);
    }
}