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
 [Statement](https://github.com/DeokgyunSHIN/DB-Connection/blob/main/Connction(Statement)/Connction(Statement).md)
 
<br>
<br>

PreparedStatement

> 쿼리에 인자값을 부여할 수 있다.
>
> 처음 프리큼파일 된 후, 이후에는 컴파일을 수행하지 않는다.
>
> 여러번 수행할 때 속도가 빠르다.

[PreparedStatement](https://github.com/DeokgyunSHIN/DB-Connection/blob/main/Connction(PreparedStatement)/Connction(PreparedStatement).md)

1. 먼저 애플리케이션은 틀만 만들고 이것을 DBMS로 보낸다. (값은 지정하지 않은 상태로 보낸다.)

2. 그다음, DBMS는 틀을 컴파일 하고 아직 실행하지 않고 결과만 저장한다.

3. 나중에 애플리케이션 틀의 변수에 값을 지정하면 DBMS는 틀을 실행한다.


즉, 정리를 한다면 

Statement를 사용하면 매번 쿼리를 수행할 떄마다 4단계를 거쳐야 한다면 

PreparedStatement는 처음 한번만 3단계만 거친 후 캐시라는 곳에 받아 재사용한다는 뜻이다. 

그러기 때문에 PreparedStatement 사용하는것을 좀더 선호 한다.
