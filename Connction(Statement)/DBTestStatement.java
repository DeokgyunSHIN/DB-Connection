import java.sql.*;

public class DBTestStatement {
    public static void main(String[] args)  {

        //1. Ip(domain) 2. port  3. 계정 4. 패스워드 5. 인스턴스

        String url="jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId="root";
        String dbPassword="tkfkd#486";

        //1. 드라이버 로드
      Connection connection=null;
      Statement statement=null;
      ResultSet rs=null;


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }
         //2 . 커넥션 객체 생성
        try {
            connection= DriverManager.getConnection(url,dbUserId,dbPassword);
            //3. 스테이트먼트 객체 생성
            statement=connection.createStatement();

            //4. 쿼리 실행
            String sql="select member_type ,user_id ,password ,name  from member" +
                    " where member_type ='email' ";
            rs= statement.executeQuery(sql);

             //5.결과 수행
            while(rs.next()){
               String memberType= rs.getString("member_type");
               String userId=rs.getString("user_id");
               String password=rs.getString("password");
               String name=rs.getString("name");

                System.out.println(memberType+", "+userId+", "+password+", "+name);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        //6. 객체 연결 해제
    } finally {
            try {
                if(rs!=null &&!rs.isClosed()){
                    rs.close();
                }
                if(statement!=null &&!statement.isClosed()){
                    statement.close();
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
