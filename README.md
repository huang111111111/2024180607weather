# WeatherApp —— 实验八 网络天气应用

姓名：黄艺艺　学号：2024180607

## 功能说明
- 主界面：子线程读取 `res/raw/city_code.json` 城市列表，使用 GSON 解析后通过 RecyclerView 展示。
- 详情界面：点击城市后通过 Volley 请求天气接口
  `http://t.weather.itboy.net/api/weather/city/{cityCode}`，GSON 解析返回 JSON，
  展示当前温度、天气类型、湿度、空气质量、PM2.5、感冒提示及未来天气预报，并按天气类型匹配本地图标。

## 技术栈
- Kotlin + 传统 View（RecyclerView）
- 网络：Volley 1.2.1
- JSON 解析：GSON 2.13.2

## 提交到 Gitee 的步骤
1. 在 Gitee 上新建一个空仓库（例如 WeatherApp），不要勾选初始化 README。
2. 在本目录执行：

```bash
git init
git add .
git commit -m "实验八 网络天气应用"
git branch -M master
git remote add origin https://gitee.com/你的用户名/WeatherApp.git
git push -u origin master
```

3. 把仓库地址填回实验报告的 “Gitee网址” 一栏。

## 说明
- `local.properties` 记录本机 SDK 路径，已在 `.gitignore` 中排除，无需提交。
- 模拟器若不能上网，可用 `-dns-server 8.8.8.8,114.114.114.114` 启动。
