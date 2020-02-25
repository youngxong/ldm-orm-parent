package com.oe;

import com.oe.config.Configuration;
import com.oe.config.ConfigurationBuilder;
import com.oe.dao.UserMapper;
import com.oe.pojo.User;
import com.oe.resource.Resources;
import com.oe.session.SqlSession;
import com.oe.session.SqlSessionFactory;
import com.oe.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrmTest {

    public  SqlSession sqlSession;

    public static void main(String[] args) throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(5);
        user.setUsername("dddd");
        sqlSession.update("com.oe.pojo.User.insert",user);
        List<User> users = sqlSession.selectList("com.oe.dao.UserMapper.findAll", user);
        for (User user1 : users) {
            System.out.println(user1.toString());
        }

    }

    @Before
    public void init(){
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
         sqlSession = sqlSessionFactory.openSession();
    }

    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void testISelect() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("xxx");
        //int i = sqlSession.insert("com.oe.dao.UserMapper.insert", user);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = userMapper.findAll();
        for (User user1 : all) {
            System.out.println(user1.toString());
        }
    }


    /**
     * 测试新增
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setId(null);
        user.setUsername("xxx");
        //int i = sqlSession.insert("com.oe.pojo.User.insert", user);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(user);
    }

    /**
     * 测试更新
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(5);
        user.setUsername("DDD");
        int i = sqlSession.update("com.oe.dao.UserMapper.update", user);
    }

    /**
     * 测试删除
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setId(4);
        user.setUsername("dddd");
        int i = sqlSession.delete("com.oe.dao.UserMapper.delete", user);
    }

    /**
     * 测试手动提交事务
     * @throws Exception
     */
    @Test
    public void testTransaction1() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession mySession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(5);
        user.setUsername("dddd");
        int i = mySession.delete("com.oe.dao.UserMapper.delete", user);
        mySession.commit();
    }

    /**
     * 测试自动提交事务
     * @throws Exception
     */
    @Test
    public void testTransaction2() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession mySession = sqlSessionFactory.openSession(true);
        User user = new User();
        user.setId(6);
        user.setUsername("dddd");
        int i = mySession.delete("com.oe.dao.UserMapper.delete", user);
    }

    /**
     * 测试代理后Mapper的事务
     * @throws Exception
     */
    @Test
    public void testTransaction3() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession mySession = sqlSessionFactory.openSession(false);
        UserMapper mapper = mySession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(3);
        user.setUsername("3w3w");
        int i = mapper.update(user);
        mySession.commit();
    }


    /**
     * 测试自定义DataSource
     * @throws Exception
     */
    @Test
    public void testCustomDataSource() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession mySession = sqlSessionFactory.openSession(false);
        UserMapper mapper = mySession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(3);
        user.setUsername("44ee44");
        int i = mapper.update(user);
        mySession.commit();
    }
}
