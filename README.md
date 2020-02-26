# ldm-orm-parent
手写orm框架

# ldm-orm-parent
手写orm框架

### Mybatis动态sql原理
    mybatis的动态sql，是基于OGNL表达式，供使用者能够更灵活的控制sql的拼接，以达到许多我们需要大量代码才能实现的功能，大大减少了我们编写代码的工作量。

常用的动态sql标签：
	choose（when，otherwise）
	trim
	where
	set
	foreach
	
动态sql的执行原理
解析阶段，mybatis在解析mapper.xml文件时，会使用XMLScriptBuilder解析动态sql，
不同的动态sql标签使用不同的NodeHandler处理，最后将动态sql按动态标签解析为多个SqlNode，然后构建成一个SqlSource，设置进Configuration中。
调用阶段，sqlsession执行时，会通过Configuration拿到对应的SqlSource，然后通过将多个SqlNode进行拼接，构建一个BoundSql对象，
BoundSql对象中存储的就是完整的sql，然后执行器Excutor执行

### Mybatis支持延迟加载。

    延迟加载的实现原理，是基于Mybatis的ResultSetHandler对结果集进行代理处理实现，
   通过DefaultResultSetHandler在对结果集处理前，增加延迟加载逻辑，这段逻辑的插入使用动态代理实现。
	
	
### Mybatis的各Executor的特点

SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。

ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象。

BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。
	
### Mybatis的缓存

    一级缓存：
	存储结构：Map结构
	作用范围：session级别，只在当前sqlSession中生效，不同sqlSession相互隔离。
	失效场景：当前sqlSession关闭，或出现更新/删除/新增操作
	
	二级缓存：
	存储结构：Map结构
	作用范围：mapper级别，无论任何sqlsession，只要操作了同一个namespace下的sql，都会被缓存，不同namespace的操作相互隔离
	失效场景：当存在sqlSession操作了某个namespace下的sql，出现更新/删除/新增操作，整个namespace下的缓存将失效
	
### Mybatis的插件原理

	Mybatis可对Executor、ParameterHandler、ResultSetHandler、StatementHandler四大接口对象进行代理增强，使用的是动态代理+责任链模式进行实现。
	
	用户自定义插件分三步：
	1.创建实体类MyPlugin,并且实现Interceptor接口   
	2.在实体类上通过@Intercepts注解，配置要拦截的对象和方法
	3.在配置文件注册插件
	
