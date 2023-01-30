# 자바와 db연동 

```java
import java.sql.*;

public class DBTest {
    public static void main(String[] args)  {

        //1. Ip(domain) 2. port  3. 계정 4. 패스워드 5. 인스턴스

        String url="jdbc:mariadb://[ip]:[port]/[테이블 명]";
        String dbUserId="[계정]";
        String dbPassword="[패스워드]";

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
```

실행을 시키면 

<img width="684" alt="스크린샷 2023-01-30 오후 7 46 58" src="https://user-images.githubusercontent.com/104719555/215456461-2ec102f3-eb96-49da-ac6a-abe1c7890bf1.png">

위의 사진처럼 나오는것을 확인할수 있다.

데이터가 맞는지 확인하기 위해서 mariadb에서 SQL문을 똑같이 해서 실행을 시키게 되면 

![스크린샷 2023-01-30 오후 7 47 06](https://user-images.githubusercontent.com/104719555/215456600-4a9bfd27-9bbd-42e6-99b6-f59dc008628f.png)

똑같은 결과가 나오는것을 확인할수 있다. 

여기서 주의 해야될점은 

String SQL 할때 마지막에 ; 를 뺴줘야 한다.

그리고 마지막에는 close로 닫아줘야한다. 

