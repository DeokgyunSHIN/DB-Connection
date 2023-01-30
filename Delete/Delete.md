# Delete


```java
   import java.sql.*;
import java.util.Scanner;

public class DBTesDelect {

    public static void DBDelect(String user_id,String pw){
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

            String sql="DELETE from member" +
                    " where user_id= ? " +
                    " and password= ? ";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user_id);
            preparedStatement.setString(2,pw);



            int affected =preparedStatement.executeUpdate();

            if(affected>0){
                System.out.println("삭제 성공");
            }else{
                System.out.println("삭제 실패");
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
        //1. Ip(domain) 2. port  3. 계정 4. 패스워드 5. 인스턴스

       // System.out.println("가입된 유형을 입력해주세요(email or kakao)");
       // String member_type=scan.next();
        System.out.println("삭제할 아이디를 입력해주세요");
        String user_id=scan.next();
        System.out.println("비밀번호를 입력해주세요.");
        String pw=scan.next();

        DBDelect(user_id,pw);
        DBSelect();
        }
 }
```

위의 코드를 실행하게 되면

![스크린샷 2023-01-30 오후 9 29 14](https://user-images.githubusercontent.com/104719555/215477542-ee5e9a5f-297f-42b2-bf33-ec31f23ab4e2.png)

위의 사진처럼 정상실행하는것을 볼수 있고 

![스크린샷 2023-01-30 오후 9 31 16](https://user-images.githubusercontent.com/104719555/215477670-8acdc316-9aa1-4b4b-90df-32bf118949b7.png)

db에서도 자동으로 변경된것을 볼수 있다.

Delete 또한 Insert와 Update랑 쿼리문만 다를뿐 나머지는 똑같다는 것을 알수 있다.



### 데이터 다시 삭제 

만약에 또한번 똑같은 데이터를 지운다면 실행결과는

![스크린샷 2023-01-30 오후 9 33 45](https://user-images.githubusercontent.com/104719555/215478502-027f5f60-fb3f-407d-97d8-65a9d7d5a358.png)

위의 사진처럼 삭제 실패라고 뜬다.

소스 자체는 이상이 없다. 한마디로 쿼리문은 에러가 나지 않는다. 

근데 왜 삭제 실패라고 뜰까?

이유는 간단하다. 쿼리문은 정상 실행되었지만 

사용자가 등록한 데이터가 db에 없기 때문에 삭제 실패라고 뜬다.
