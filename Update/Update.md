# update 

```java 
  import java.sql.*;
import java.util.Scanner;

public class DBTestUpdate {

    public static void DBUpdate(String user_id,String pw){
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

            String sql="update member" +
                    " set password= ? " +
                    " where user_id= ? ";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,pw);
            preparedStatement.setString(2,user_id);



            int affected =preparedStatement.executeUpdate();

            if(affected>0){
                System.out.println("변경 성공");
            }else{
                System.out.println("변경 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null &&!rs.isClosed()){
                    rs.close();
                }
                if(preparedStatement!=null &&!preparedStatement.isClosed()){
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
    public static void DBSelect(){
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

    public static void main(String[] args)  {
     Scanner scan=new Scanner(System.in);
    
        System.out.println("비밀번호 변경할 아이디를 입력해주세요.");
        String user_id=scan.next();
        System.out.println("변경할 비밀번호를 입력해주세요.");
        String pw=scan.next();
        DBUpdate(user_id,pw);
        DBSelect();
        }
 }
```

위의 코드를 실행하게 되면 

![스크린샷 2023-01-30 오후 9 06 25](https://user-images.githubusercontent.com/104719555/215473017-961e5c6c-e550-49f8-900a-3a8a311ed186.png)

pw가 변경된 것을 알수 있고 

![스크린샷 2023-01-30 오후 9 09 34](https://user-images.githubusercontent.com/104719555/215473201-c1395d9e-3004-467c-b78e-9ce1b5b6e4a3.png)

db 또한 변경되어져 있는것을 알수 있다.

코드를 보면 insert와 똑같다 다만 다른것은 쿼리문 만 다를뿐 나머지는 다른것이 없다.
