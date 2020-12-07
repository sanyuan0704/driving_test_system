# driving_test_system

科目三驾考系统。

## 系统运行要求
- Android 6.0及以上
- 平板设备最佳

## 项目结构说明
```
├─app
│  ├─...
│  └─src
│      ├─...
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─whut
│      │  │          └─driving_test_system:主目录
│      │  │              ├─models:主要处理和数据库Room相关的操作
│      │  │              │  ├─daos
│      │  │              │  ├─eneities
│      │  │              │  └─repositories
│      │  │              ├─ui:相关UI组件和控制器
│      │  │              │   ├─adapters
│      │  │              │   ├─fragments
│      │  │              │   └─viewmodels
│      │  │              └─MainActivity.java:程序入口
│      │  └─res
│      │      ├─...
└─gradle
    └─wrapper
```