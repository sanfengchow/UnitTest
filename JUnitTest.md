### 前言

测试VS单元测试

`测试`是测试部门的事情;`单元测试`是每个开发的事情

单元测试是项目成功、个人成功的一个不可或缺的部分,单元测试相对廉价而简单的技术,但它能让你更高效的写出质量更好的代码.

开发不写单元测试的原因

- 项目进度压力
- 写单元测试很蛮烦,我想专注写`Bug`
- 认可该做更多测试,这种让人人同意的事情很多,比如多吃蔬菜、该多锻炼,往往很多人会做.
- 不知道写

单元测试设计目的并不是为了获得一些更好的整理质量.也就是说,它并不是针对最终用户、项目经理和开发组长的工具;而是由程序员自己完成,并且最终收益的也是程序员自己.

什么是单元测试?

`单元测试`是开发者编写的一小段代码,用于检验被测代码的一个很小、很明确的功能是否正确.

`执行单元测试`是为了证明某段代码的行为确实和开发所期望的一致.

为什么要单元测试?

- 工作变得轻松、设计变得更好、减少花费在调试上面的时间(简单有效的技术让代码变得更加完美)
- 洛与霞
- 带来更高的测试覆盖率
- 提高团队效率
- 监测衰退和减少调试
- 自信的重构
- 改进实现
- 将预期的行为文档化



`Assert.assertEquals()` 比较2个对象是否相等.

`Assert.assertNull()` 验证一个对象是否为空

`Assert.assertSame()` 参数所引用的是否为同一个对象



好的测试品质A-TRIP

- 自动化(Automatic)
- 彻底的(Thorough)
- 可重复(Repeatable)
- 独立的(Independent)
- 专业的(Professional)

#### Mockito

```java
@MockBean

@Before
public void initMock() {
    MockitoAnnotations.initMocks(this);
}
```

Spring boot只会扫描启动类当前包和以下的包

Springboot默认扫描规则是：自动扫描启动器类的同包或者其子包的下的注解

如果将 spring boot 放在 包  com.test.controller 里面的话 ，它会扫描 com.test.controller 和 com.test.controller.* 里面的所有的，可以添加@ComponentScan(basePackages = { "XXXXXX" })解决，即扫描包



`@BeforeClass` 在所有测试方法前执行一次，一般在其中写上整体初始化的代码

`@AfterClass `在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码

`@Before `在每个测试方法前执行，一般用来初始化方法（比如我们在测试别的方法时，类中与其他测试方法共享的值已经被改变，为了保证测试结果的有效性，我们会在@Before注解的方法中重置数据）

`@After` 在每个测试方法后执行，在方法执行完成后要做的事情

`@Test(timeout = 1000)` 测试方法执行超过1000毫秒后算超时，测试将失败

`@Test(expected = Exception.class) `测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败

`@Ignore(“not ready yet”) `执行测试时将忽略掉此方法，如果用于修饰类，则忽略整个类

`@Test `编写一般测试用例

`@RunWith `在JUnit中有很多个Runner，他们负责调用你的测试代码，每一个Runner都有各自的特殊功能，你要根据需要选择不同的Runner来运行你的测试代码,SpringBoot项目主要用`@SpringRunner` ,`@SpringRunner`是`@SpringJUnit4ClassRunner`的一个别名。



@SpringBootApplication注解等价于以默认属性使用`@Configuration`，`@EnableAutoConfiguration`和`@ComponentScan`。



您可以使用**@SpringBootTest**的**webEnvironment**属性来进一步优化测试的运行方式：

- **MOCK** ： 加载一个WebApplicationContext并提供一个模拟servlet环境。嵌入式servlet容器在使用此注释时不会启动。如果servlet API不在你的类路径上，这个模式将透明地回退到创建一个常规的非web应用程序上下文。可以与@AutoConfigureMockMvc结合使用，用于基于MockMvc的应用程序测试。
- **RANDOM_PORT ：** 加载一个EmbeddedWebApplicationContext并提供一个真正的servlet环境。嵌入式servlet容器启动并在随机端口上侦听。
- **DEFINED_PORT ：** 加载一个EmbeddedWebApplicationContext并提供一个真正的servlet环境。嵌入式servlet容器启动并监听定义的端口（即从application.properties或默认端口8080）。
- **NONE ：** 使用SpringApplication加载ApplicationContext，但不提供任何servlet环境（模拟或其他）。





![](/Users/zhoushuai/SmartWork/Topic/UnitTest/images/立即测试与单一测试比较.png)

![](/Users/zhoushuai/SmartWork/Topic/UnitTest/images/JUnit断言.png)



#### 注意事项

1、如果你的测试是[@Transactional](https://my.oschina.net/u/3770144)，默认情况下它会在每个测试方法结束时回滚事务。 但是，由于使用RANDOM_PORT或DEFINED_PORT这种安排隐式地提供了一个真正的servlet环境，所以HTTP客户端和服务器将在不同的线程中运行，从而分离事务。 在这种情况下，在服务器上启动的任何事务都不会回滚。



- `Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test`

>  `@SpringBootTest`搜索`@SpringBootApplication`注解的类，是按照test所在类的package往父层级逐级搜索。 所以解决办法一：`@SpringBootTest(classes = Application.class)`，解决方案二：修改test所在类的package，放到`@SpringBootApplication`子package  





### 单元测试原理

<https://blog.csdn.net/u012501054/article/details/79799241>