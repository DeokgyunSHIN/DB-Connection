import java.sql.*;
import java.util.Scanner;

public class DBTestInsert {

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
