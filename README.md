# DB-Connection

DB연결 설정 

> DB를 연결하기 위해서는 JDBC 드라이버가 필요하다.
>
> 5가지의 정보를 통해서 DB에 접속한다.(IP,PORT,INSTANCE,USER_ID,PASSWORD)

```
 String url ="jdbc:mariadb:// ip : 포트번호 / instance";
```

### Statement와 PreparedStatement 차이점 

Statement

> 단일로 사용할 때 속도가 빠르다.
>
> 매번 컴파일을 수행해야한다.

<br>
<br>

PreparedStatement

> 쿼리에 인자값을 부여할 수 있다.
>
> 처음 프리큼파일 된 후, 이후에는 컴파일을 수행하지 않는다.
>
> 여러번 수행할 때 속도가 빠르다.

[PreparedStatement]
