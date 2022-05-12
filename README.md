## 描述

首先，我没有配置数据源信息，也没有使用SpringData JPA进行测试

TestServiceImpl.java
````java
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String test() {
        System.out.println(StpUtil.isLogin());
        int id = 1;
        String prefix = "redis_data";
//        User user = new User(1, "yxr", "123");
//        userDao.save(user);
        if(Boolean.TRUE.equals(serializableRedisTemplate.hasKey(prefix + id))){
        serializableRedisTemplate.opsForValue().set("hello","world");
        }
        System.out.println(StpUtil.isLogin());
        System.out.println(StpUtil.getLoginId());
        return "ok";
    }
````
此时，是不会抛异常的


后面，我加入了SpringData JPA和数据源进行测试

相比较于上述代码，多了`userDao.save(user);`这一步操作

此时，再进行请求，就会出现issue所描述的bug

在操作redis之前的打印`System.out.println(StpUtil.isLogin());` 是可以正常打印的

然后在操作redis后，就会抛出`NotLoginException`


````java
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String test() {
        System.out.println(StpUtil.isLogin());
        int id = 1;
        String prefix = "redis_data";
        User user = new User(1, "yxr", "123");
        userDao.save(user);
        if(Boolean.TRUE.equals(serializableRedisTemplate.hasKey(prefix + id))){
        serializableRedisTemplate.opsForValue().set("hello","world");
        }
        System.out.println(StpUtil.isLogin());
        System.out.println(StpUtil.getLoginId());
        return "ok";
    }
````

不太清楚问题是哪里，麻烦帮忙调试看看

个人表达能力有限，如果有不明确的地方，麻烦联系我