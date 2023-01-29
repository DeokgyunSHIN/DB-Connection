# DB-Connection

DB연결 설정 

> DB를 연결하기 위해서는 JDBC 드라이버가 필요하다.
>
> 5가지의 정보를 통해서 DB에 접속한다.(IP,PORT,INSTANCE,USER_ID,PASSWORD)

```
 String url ="jdbc:mariadb:// ip : 포트번호 / instance";
```

### java를 통한 JDBC 프로그램 순서 -(CRUD)

1. JDBC 드라이버 로드  (Class.forName("org.mariadb.Driver");

2. DataBase Connection 생성 (Connection connection=DriverManager.getConnection(url,dbUser,dbPassword);)

3. SQL을 위한 Statement 객체 생성 (PreraredStatement preparedStatement= connection.prepareStatement(sql);)

4. SQL 문장 실행 (ResultSet resultSet = paeparedStatement.executeQuery();)

(create 라면  

5. SQL 실행 결과 처리 (while(rs.next()){ System.out.println(rs.getString("user_id"));)

6. JDBC 객체들 연결 해제 ( resultSet.close();  preparedStatement.close();  connection.close();)
