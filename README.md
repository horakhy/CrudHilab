# CrudHilab

**Uma API CRUD simples em Java que utiliza as Tables presente em um servidor MySQL e exportam os dados para um arquivo .json**

## User.java
*Contém a classe que representa o Usuário presente no sistema juntamente a seus campos*


## ConnectionFactory
*Faz a conexão com o servidor MySQL*

Utilizados para a conexão com o servidor e funções do Driver e o tratamento de exceções 
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
```

## UserDAO.java
*Contém as funções que realizam o CRUD, junto a funções suporte que realizam o verificação do input do usuário*

Utilizados para a conexão com o servidor e a execução das queries
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
```

Utilizados para a verificação do input do usuário e lançamento de exceções
```java
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
```

Utilizados para receber inputs do usuário (Scanner) e verificação do input (regex.*), armazenar as opções de categoria profissional (Map) e lançamento de exceções 
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
```

## JsonHandler.java
*Contém a classe que cria o arquivo .json a partir dos dados da table do servidor MySQL*

Utilizados para a criação e tratamento de exceção do arquivo .json
```java
import java.io.FileWriter;
import java.io.IOException;
```

Utilizados para a conexão e cópia dos dados presentes no MySQL para o arquivo .json
```java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
```

Utilizados para o tratamento de exceções e passar para uma formatação própria do .json as informações das tables do servidor 
```java
import org.json.JSONException;
import org.json.simple.JSONValue;
```

Utilizados para armazenar as informações das colunas do MySQL
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
```


