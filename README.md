# sql毕业设计

##1.数据库设计
    teacher表：
                tid 教师工号 非空长度20
                password 密码 非空
                nickname 昵称 非空
                email    邮箱 非空
                createDate 创建时间
                permission 权限
    student：
                sid 学生学号 非空长度20
                password 密码 非空
                nickname 昵称 非空
                email    邮箱 非空
                createDate 创建时间
                permission 权限
    answer：
              aid 答题编号 非空长度20
              pid 回答的题目 非空长度20
              sid 答题学生 非空长度20
              language 数据库语言 非空
              input 学生的SQL语句
              output 学生的SQL语句的执行结果
              email    邮箱 非空
              score 分数
              createDate 创建时间   
    problem：
              pid 答题编号 非空长度20
              title 题目标题 
              input 标准SQL语句
              output 标准SQL语句的执行结果
              Description 题目描述
              hint 扩展属性提示等等
              createDate 创建时间   
              language 数据库语言 非空
                   