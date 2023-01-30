# Insert (select 와 기본 응용)

``` 
 import java.sql.*;
import java.util.Scanner;

public class DBTestInsert {  //insert

    public static void DBInsert(String member_type,String user_id,String pw,String name){
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

            String sql="INSERT into member(member_type,user_id,password,name)" +
                    " values(?,?,?,?)";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,member_type);
            preparedStatement.setString(2,user_id);
            preparedStatement.setString(3,pw);
            preparedStatement.setString(4,name);


            int affected =preparedStatement.executeUpdate();

            if(affected>0){
                System.out.println("저장 성공");
            }else{
                System.out.println("저장 실패");
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
    public static void DBSelect(){ //select 
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
        System.out.println("kakao 또는 email 선택하세요.");
        String member_type=scan.next();
        System.out.println("아이디를 입력하세요.");
        String user_id =scan.next();
        System.out.println("패스워드를 입력하세요");
        String pw= scan.next();
        System.out.println("이름음 입력하세요.");
        String name=scan.next();
        DBInsert(member_type,user_id,pw,name);
        DBSelect();
        }
 }
```

위의 코드를 실행하게 되면 

![스크린샷 2023-01-30 오후 8 32 45](https://user-images.githubusercontent.com/104719555/215466143-654da507-36cc-461c-bca2-5cf8a8c43464.png)

위의 사진처럼 결과가 나오게 되며

![스크린샷 2023-01-30 오후 8 35 33](https://user-images.githubusercontent.com/104719555/215466263-3b7805b1-ccba-4a1f-9f17-c4a248f2ce96.png)

DB 또한 데이터가 자동적으로 들어간것을 볼수 있다.

여기서 알고 가야 할 부분은 

insert는  executeUpdate(); 를 사용해줘야 하는데 

executeUpdate();의 반환 값이 int 이고 성공을 하게 되면 1로 리턴이 된다.

select 는 반대로 executeQuery();를 사용해줘야한다.
