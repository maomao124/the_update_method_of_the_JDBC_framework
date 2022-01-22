import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

/**
 * Project name(项目名称)：JDBC框架之update方法
 * Package(包名): PACKAGE_NAME
 * Class(类名): JDBCTemplate
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/22
 * Time(创建时间)： 20:41
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class JDBCTemplate
{
    /**
     * JDBC框架 update方法
     *
     * @param sql  sql语句
     * @param objs sql中的问号占位符，数量和顺序一定要一致
     * @return 影响行数
     */
    public static int update(String sql, Object... objs)
    {
        //影响行数,初始值为0
        int result = 0;
        //连接对象
        Connection connection = null;
        //预编译执行者对象
        PreparedStatement preparedStatement = null;
        try
        {
            //获取连接对象(Druid连接池)
            connection = Druid.getConnection();
            //或者(自定义数据库连接池)：
            //connection = connectionPool.getConnection();
            //或者直接获取(自定义JDBC工具类)：
            //connection = JDBC.getConnection();

            //预编译sql，或者执行者对象
            preparedStatement = connection.prepareStatement(sql);
            //获取参数的源信息对象
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            //获取参数个数
            int count = parameterMetaData.getParameterCount();
            //判断参数是否一致，如果不一致，异常抛出
            if (objs.length != count)
            {
                throw new RuntimeException("update方法中参数个数不一致!");
            }
            //为问号占位符赋值
            for (int i = 0; i < count; i++)
            {
                preparedStatement.setObject(i + 1, objs[i]);
            }
            //执行sql语句
            result = preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //释放资源
            Druid.close(connection, preparedStatement);
            //或者：
            //JDBC.close(connection, preparedStatement);
        }
        //返回结果
        return result;
    }
}
