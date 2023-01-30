import java.sql.*;

public class DBTestPreparedStatement {
    public static void main(String[] args)  {

        //1. Ip(domain) 2. port  3. 계정 4. 패스워드 5. 인스턴스

        String url="jdbc:mariadb://[ip]:[port]/[테이블 명]";
        String dbUserId="[계정]";
        String dbPassword="[password]";


      Connection connection=null;
      PreparedStatement preparedStatement=null;
      ResultSet rs=null;


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }

        try {
            connection= DriverManager.getConnection(url,dbUserId,dbPassword);


            String sql="select member_type ,user_id ,password ,name  from member" +
                    " where member_type = ?  ";

            preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1,"email");
           rs=preparedStatement.executeQuery();

            while(rs.next()){
               String memberType= rs.getString("member_type");
               String userId=rs.getString("user_id");
               String password=rs.getString("password");
               String name=rs.getString("name");

                System.out.println(memberType+", "+userId+", "+password+", "+name);
            }
        } catch (SQLException e) {
           e.printStackTrace();

    } finally {
            try {
                if(rs!=null &&!rs.isClosed()){
                    rs.close();
                }
                if( preparedStatement!=null &&!preparedStatement.isClosed()){
                    preparedStatement.close();
                }
                if(connection!=null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        }
}
