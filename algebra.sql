/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : algebra

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2021-04-13 22:09:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` char(6) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123456', '123');

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `answer_content` varchar(1000) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`answer_id`),
  KEY `user_answer` (`user_id`),
  KEY `question_answer` (`question_id`) USING BTREE,
  CONSTRAINT `question_answer` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_answer` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '1', '好厉害', '80', '2021-04-01 21:22:57');
INSERT INTO `answer` VALUES ('2', '1', '老哥666', '80', '2021-04-01 21:56:22');
INSERT INTO `answer` VALUES ('3', '1', '懂了', '84', '2021-04-01 21:56:35');
INSERT INTO `answer` VALUES ('4', '1', '原来如此', '105', '2021-04-01 21:56:45');
INSERT INTO `answer` VALUES ('5', '1', '终于会了，666', '80', '2021-04-01 21:57:04');
INSERT INTO `answer` VALUES ('6', '1', '牛', '91', '2021-04-01 21:57:16');
INSERT INTO `answer` VALUES ('7', '1', '天秀啊', '80', '2021-04-01 23:12:37');
INSERT INTO `answer` VALUES ('8', '1', '强啊兄弟', '91', '2021-04-01 23:13:23');
INSERT INTO `answer` VALUES ('9', '2', '学会了学会了', '91', '2021-04-01 23:13:40');
INSERT INTO `answer` VALUES ('10', '2', '芜湖', '91', '2021-04-01 23:14:33');
INSERT INTO `answer` VALUES ('11', '2', '原来这样', '91', '2021-04-01 23:14:47');
INSERT INTO `answer` VALUES ('12', '3', '这个模板不错啊', '91', '2021-04-01 23:15:38');
INSERT INTO `answer` VALUES ('13', '3', '挺厉害的', '91', '2021-04-01 23:15:49');
INSERT INTO `answer` VALUES ('14', '3', '强强强', '91', '2021-04-01 23:16:37');
INSERT INTO `answer` VALUES ('15', '3', '厉害', '91', '2021-04-01 23:17:27');
INSERT INTO `answer` VALUES ('16', '4', '我会了', '91', '2021-04-01 23:17:37');
INSERT INTO `answer` VALUES ('17', '4', '哈哈哈', '91', '2021-04-01 23:17:42');
INSERT INTO `answer` VALUES ('18', '6', '原来这么简单', '91', '2021-04-01 23:18:00');
INSERT INTO `answer` VALUES ('19', '2', '56', '106', '2021-04-02 12:09:40');
INSERT INTO `answer` VALUES ('20', '13', '牛逼', '80', '2021-04-02 12:28:19');
INSERT INTO `answer` VALUES ('21', '9', '可以666', '80', '2021-04-04 19:43:58');
INSERT INTO `answer` VALUES ('22', '10', '很好理解', '91', '2021-04-07 20:03:15');
INSERT INTO `answer` VALUES ('23', '2', '牛啊', '80', '2021-04-08 23:00:03');
INSERT INTO `answer` VALUES ('24', '2', '666', '80', '2021-04-08 23:00:13');

-- ----------------------------
-- Table structure for `answers`
-- ----------------------------
DROP TABLE IF EXISTS `answers`;
CREATE TABLE `answers` (
  `answers_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_id` int(11) NOT NULL,
  `answers_content` varchar(1000) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`answers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answers
-- ----------------------------

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `algorithm_id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `algorithm_content` text NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`algorithm_id`),
  KEY `file_user_id` (`user_id`),
  CONSTRAINT `file_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('22', '1507bd7d55524859bcc3e10d1ea4f895', '80', '由易而难 线性代数常常涉及大型数组，故先将容易的问题搞明白，再解决有难度的问题，例如行列式定义，首先将3阶行列式定义理解好，自然可以推广到n阶行列式情形；由低而高 运用技巧，省时不少，无论是行列式还是矩阵，在低阶状态，找出适合的计算方法，则可自如推广运用到高阶情形；由简而繁 一些运算法则，先试用于简单情形，进而应用于复杂问题，例如，克莱姆法则，线性方程组解存在性判别，对角化问题等等；', '2021-04-12 22:34:11');
INSERT INTO `file` VALUES ('23', 'f3a30834ecaa441992d96a2d25fe7af5', '107', '由易而难 线性代数常常涉及大型数组，故先将容易的问题搞明白，再解决有难度的问题，例如行列式定义，首先将3阶行列式定义理解好，自然可以推广到n阶行列式情形；由低而高 运用技巧，省时不少，无论是行列式还是矩阵，在低阶状态，找出适合的计算方法，则可自如推广运用到高阶情形；由简而繁 一些运算法则，先试用于简单情形，进而应用于复杂问题，例如，克莱姆法则，线性方程组解存在性判别，对角化问题等等；', '2021-04-13 19:19:05');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_content` varchar(100) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', 'id为 85 83的用户被删除了', '2021-03-25 08:40:04');
INSERT INTO `message` VALUES ('2', 'id为84的用户信息被更新了', '2021-03-25 08:56:21');
INSERT INTO `message` VALUES ('3', 'id为 86 87的用户被删除了', '2021-03-25 08:56:29');
INSERT INTO `message` VALUES ('4', '添加了用户名为lisi8的用户', '2021-03-25 08:56:59');
INSERT INTO `message` VALUES ('5', 'id为93的用户信息被更新了', '2021-03-25 08:57:20');
INSERT INTO `message` VALUES ('6', '添加了用户名为456的用户', '2021-03-25 15:09:46');
INSERT INTO `message` VALUES ('7', '添加了用户名为456456的用户', '2021-03-25 15:09:53');
INSERT INTO `message` VALUES ('8', '添加了用户名为187的用户', '2021-03-25 15:09:59');
INSERT INTO `message` VALUES ('9', '添加了用户名为418的用户', '2021-03-25 15:10:11');
INSERT INTO `message` VALUES ('10', 'id为 96的用户被删除了', '2021-03-25 15:10:23');
INSERT INTO `message` VALUES ('11', 'id为 97的用户被删除了', '2021-03-25 15:10:27');
INSERT INTO `message` VALUES ('12', '添加了用户名为74186的用户', '2021-03-25 15:10:39');
INSERT INTO `message` VALUES ('13', '添加了用户名为7197419749的用户', '2021-03-25 15:10:45');
INSERT INTO `message` VALUES ('14', '添加了用户名为418989的用户', '2021-03-25 15:10:50');
INSERT INTO `message` VALUES ('15', '添加了用户名为18648的用户', '2021-03-25 15:11:20');
INSERT INTO `message` VALUES ('16', 'id为 101的用户被删除了', '2021-03-25 15:15:24');
INSERT INTO `message` VALUES ('17', 'id为 103的用户被删除了', '2021-03-25 15:16:15');
INSERT INTO `message` VALUES ('18', '添加了用户名为1467965694@qq.com的用户', '2021-03-25 15:16:24');
INSERT INTO `message` VALUES ('19', 'id为 104的用户被删除了', '2021-03-25 15:16:30');
INSERT INTO `message` VALUES ('20', 'id为84的用户信息被更新了', '2021-03-25 15:18:53');
INSERT INTO `message` VALUES ('21', '添加了用户名为1478的用户', '2021-03-25 15:23:44');
INSERT INTO `message` VALUES ('22', 'id为80的用户发布了文章<<开心的金明（01背包DP问题）>>', '2021-03-31 00:05:57');
INSERT INTO `message` VALUES ('23', '添加了用户名为misaki的用户', '2021-04-02 12:07:48');
INSERT INTO `message` VALUES ('24', 'id为106的用户发布了文章<<888>>', '2021-04-02 12:15:52');
INSERT INTO `message` VALUES ('25', 'id为80的用户上传了文件1.txt', '2021-04-07 18:18:09');
INSERT INTO `message` VALUES ('26', 'id为80的用户上传了文件1.txt', '2021-04-07 19:55:26');
INSERT INTO `message` VALUES ('27', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 11:32:06');
INSERT INTO `message` VALUES ('28', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 11:58:06');
INSERT INTO `message` VALUES ('29', 'id为80的用户上传了文件1.txt', '2021-04-08 11:58:08');
INSERT INTO `message` VALUES ('30', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 11:58:10');
INSERT INTO `message` VALUES ('31', 'id为91的用户上传了文件1.txt', '2021-04-08 22:00:58');
INSERT INTO `message` VALUES ('32', 'id为91的用户上传了文件二分查找模板.txt', '2021-04-08 22:01:00');
INSERT INTO `message` VALUES ('33', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 22:15:28');
INSERT INTO `message` VALUES ('34', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 22:40:38');
INSERT INTO `message` VALUES ('35', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 22:42:23');
INSERT INTO `message` VALUES ('36', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-08 22:51:32');
INSERT INTO `message` VALUES ('37', 'id为21的算法文件被删除了', '2021-04-08 22:51:56');
INSERT INTO `message` VALUES ('38', 'id为80的用户上传了文件二分查找模板.txt', '2021-04-12 22:34:11');
INSERT INTO `message` VALUES ('39', '添加了用户名为王五的用户', '2021-04-13 19:18:39');
INSERT INTO `message` VALUES ('40', 'id为107的用户上传了文件二分查找模板.txt', '2021-04-13 19:19:05');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(100) NOT NULL,
  `question_content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`question_id`),
  KEY `user_question` (`user_id`),
  CONSTRAINT `user_question` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '动态规划模板', '动态规划', '80', '2021-03-27 18:08:16');
INSERT INTO `question` VALUES ('2', '双指针模板', '<h3><a id=\"_0\"></a>常见问题分类：</h3>\n<ul>\n<li>对于一个序列，用两个指针维护一段区间</li>\n<li>对于两个序列，维护某种次序，比如归并排序中合并两个有序序列的操作</li>\n</ul>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-keyword\">for</span> (<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>, j = <span class=\"hljs-number\">0</span>; i &lt; n; i ++ )\n{\n    <span class=\"hljs-keyword\">while</span> (j &lt; i &amp;&amp; check(i, j)) j ++ ;\n\n    <span class=\"hljs-comment\">// 具体问题的逻辑</span>\n}\n</code></div></pre>', '80', '2021-03-27 18:08:46');
INSERT INTO `question` VALUES ('3', '浮点数二分模板', '<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-function\"><span class=\"hljs-keyword\">double</span> <span class=\"hljs-title\">bsearch_3</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">double</span> l, <span class=\"hljs-keyword\">double</span> r)</span>\n</span>{\n    <span class=\"hljs-keyword\">const</span> <span class=\"hljs-keyword\">double</span> eps = <span class=\"hljs-number\">1e-6</span>;   <span class=\"hljs-comment\">// eps 表示精度，取决于题目对精度的要求</span>\n    <span class=\"hljs-keyword\">while</span> (r - l &gt; eps)\n    {\n        <span class=\"hljs-keyword\">double</span> mid = (l + r) / <span class=\"hljs-number\">2</span>;\n        <span class=\"hljs-keyword\">if</span> (check(mid)) r = mid;\n        <span class=\"hljs-keyword\">else</span> l = mid;\n    }\n    <span class=\"hljs-keyword\">return</span> l;\n}\n</code></div></pre>', '80', '2021-03-27 18:09:01');
INSERT INTO `question` VALUES ('4', '整数二分模板', '<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-comment\">// 区间[l, r]被划分成[l, mid]和[mid + 1, r]时使用：</span>\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">int</span> <span class=\"hljs-title\">bsearch_1</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> l, <span class=\"hljs-keyword\">int</span> r)</span>\n</span>{\n    <span class=\"hljs-keyword\">while</span> (l &lt; r)\n    {\n        <span class=\"hljs-keyword\">int</span> mid = l + r &gt;&gt; <span class=\"hljs-number\">1</span>;\n        <span class=\"hljs-keyword\">if</span> (check(mid)) r = mid;    <span class=\"hljs-comment\">// check()判断mid是否满足性质</span>\n        <span class=\"hljs-keyword\">else</span> l = mid + <span class=\"hljs-number\">1</span>;\n    }\n    <span class=\"hljs-keyword\">return</span> l;\n}\n<span class=\"hljs-comment\">// 区间[l, r]被划分成[l, mid - 1]和[mid, r]时使用：</span>\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">int</span> <span class=\"hljs-title\">bsearch_2</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> l, <span class=\"hljs-keyword\">int</span> r)</span>\n</span>{\n    <span class=\"hljs-keyword\">while</span> (l &lt; r)\n    {\n        <span class=\"hljs-keyword\">int</span> mid = l + r + <span class=\"hljs-number\">1</span> &gt;&gt; <span class=\"hljs-number\">1</span>;\n        <span class=\"hljs-keyword\">if</span> (check(mid)) l = mid;\n        <span class=\"hljs-keyword\">else</span> r = mid - <span class=\"hljs-number\">1</span>;\n    }\n    <span class=\"hljs-keyword\">return</span> l;\n}\n</code></div></pre>', '80', '2021-03-27 18:09:06');
INSERT INTO `question` VALUES ('5', '快速幂模板', '快速幂', '80', '2021-03-27 18:09:12');
INSERT INTO `question` VALUES ('6', '快速排序模板', '<h4><a id=\"_0\"></a>思路</h4>\n<p>分治思想，每次选取一个数x，在数组中随意选</p>\n<p>通过交换让数组 <strong>左边部分全部小于等于x，右边部分全部大于等于x</strong></p>\n<p>再将 <strong>左边部分和右边部分递归</strong> 上述过程，当递归完成时，数组也排好序了</p>\n<h4><a id=\"_8\"></a>注意点</h4>\n<p><strong>不用一般while的原因</strong></p>\n<p>每次i和j交换后都应该往后跳一步<br />\n而如果使用一般while会出现卡死的问题<br />\n当交换的两个数等于选取的分界数x时，将出现死循环</p>\n<p>一般while也可以</p>\n<p>while(arr[++ i] &lt; x);</p>\n<p>while(arr[-- j] &gt; x);</p>\n<p><strong>i，j的初始化</strong></p>\n<p>因为现在使用是dowhile，所以执行前要将i，j往外扩展一位</p>\n<p>使得第一次执行do后 i，j指向目标位置（即当前数组的首尾）</p>\n<p><strong>判断条件</strong></p>\n<p>判断条件中不需要加上等号</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">import</span> java.util.*;\n\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">Main</span></span>{\n    <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">int</span>[] arr = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">int</span>[<span class=\"hljs-number\">10000000</span>];\n\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span></span>{\n        Scanner sc = <span class=\"hljs-keyword\">new</span> Scanner(System.in);\n        <span class=\"hljs-keyword\">int</span> n = sc.nextInt();\n        <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>; i &lt; n; i ++){\n            arr[i] = sc.nextInt();\n        }\n        quickSort(arr, <span class=\"hljs-number\">0</span>, n - <span class=\"hljs-number\">1</span>);\n        <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>; i &lt; n; i ++){\n            System.out.print(arr[i]);\n            <span class=\"hljs-keyword\">if</span>(i &lt; n - <span class=\"hljs-number\">1</span>){\n                System.out.print(<span class=\"hljs-string\">\" \"</span>);\n            }\n        }\n    }\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">quickSort</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span>[] arr, <span class=\"hljs-keyword\">int</span> l, <span class=\"hljs-keyword\">int</span> r)</span></span>{\n        <span class=\"hljs-keyword\">if</span>(l &gt;= r){\n            <span class=\"hljs-keyword\">return</span>;\n        }\n        <span class=\"hljs-keyword\">int</span> x = arr[l], i = l - <span class=\"hljs-number\">1</span>, j = r + <span class=\"hljs-number\">1</span>;\n        <span class=\"hljs-keyword\">while</span>(i &lt; j){\n            <span class=\"hljs-keyword\">do</span>{\n                i ++;\n            }<span class=\"hljs-keyword\">while</span>(arr[i] &lt; x);\n            <span class=\"hljs-keyword\">do</span>{\n                j --;\n            }<span class=\"hljs-keyword\">while</span>(arr[j] &gt; x);\n            <span class=\"hljs-keyword\">if</span>(i &lt; j){\n                <span class=\"hljs-keyword\">int</span> temp = arr[i];\n                arr[i] = arr[j];\n                arr[j] = temp;\n            }\n        }\n        quickSort(arr, l, j);\n        quickSort(arr, j + <span class=\"hljs-number\">1</span>, r);\n    }\n}\n</code></div></pre>', '80', '2021-03-27 18:09:17');
INSERT INTO `question` VALUES ('7', '高精度模板', '高精度', '80', '2021-03-27 18:09:34');
INSERT INTO `question` VALUES ('8', '判定质数模板', '<h4><a id=\"_2\"></a>定义</h4>\r\n<p>质数（素数）指的是<strong>严格大于1的只包含1和本身两个约数的数</strong></p>\r\n<h4><a id=\"_6\"></a>优化</h4>\r\n<p>由于约数是两两成对	所以只需要枚举到本身的根号阶	即可判断出结果</p>\r\n<p><strong>循环条件可选写法</strong></p>\r\n<ol>\r\n<li>\r\n<p>i * i &lt;= n<br />\r\n如果n接近于int型最大值	那么i * i 有可能会溢出变成负数	影响判定结果</p>\r\n</li>\r\n<li>\r\n<p>使用函数</p>\r\n<p>​	每次判断时都要调用函数	耗时久</p>\r\n</li>\r\n<li>\r\n<p>i &lt;= n / i (推荐写法)<br />\r\n不会溢出</p>\r\n</li>\r\n</ol>\r\n<h4><a id=\"_22\"></a>代码</h4>\r\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-comment\">//试除法判定素数</span>\r\n<span class=\"hljs-comment\">// 注意这里的优化细节</span>\r\n<span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>; i &lt;= n / i ; i ++){\r\n    <span class=\"hljs-keyword\">if</span>(n % i == <span class=\"hljs-number\">0</span>){\r\n        <span class=\"hljs-keyword\">return</span> <span class=\"hljs-keyword\">false</span>;\r\n    }\r\n    <span class=\"hljs-keyword\">return</span> <span class=\"hljs-keyword\">true</span>;\r\n}\r\n</code></div></pre>', '80', '2021-03-30 22:16:45');
INSERT INTO `question` VALUES ('9', '分解质因数模板', '<h4><a id=\"_2\"></a>注意点</h4>\r\n<p>（1）从2开始枚举</p>\r\n<p>（2）每次找到质因子后计算质因子的个数时 都会将n缩小，即将算到的质因子去除，更新后n不会再有此质因子</p>\r\n<p>所以</p>\r\n<ol>\r\n<li>可以直接从0枚举到n / i，不用考虑n % i == 0的 i 可能是合数的问题，因为每次都将之前的质因子去掉了</li>\r\n<li>算法的时间复杂度不一定就是O(sqrt(n)) 即根号阶，因为可能在过程中n缩小了，降低了判断次数，最好情况下的时间复杂度为O(logn)</li>\r\n</ol>\r\n<p>（3）n中只可能有一个大于sqrt(n)的质因子    （如果有多于一个，则相乘大于n，矛盾了）</p>\r\n<p>这个质因子只可能是	循环完成后	去除了其他质因子的n</p>\r\n<h4><a id=\"_17\"></a>代码</h4>\r\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">divide</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> n)</span></span>{\r\n    <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">2</span>; i &lt;= n / i; i ++){\r\n        <span class=\"hljs-keyword\">if</span>(n % i == <span class=\"hljs-number\">0</span>){\r\n            <span class=\"hljs-keyword\">int</span> s = <span class=\"hljs-number\">0</span>;\r\n            <span class=\"hljs-keyword\">while</span>(n % i == <span class=\"hljs-number\">0</span>){\r\n                n /= i;\r\n                s ++;\r\n            }\r\n        }\r\n        System.out.println(i + <span class=\"hljs-string\">\",\"</span> + s);\r\n    }\r\n    <span class=\"hljs-keyword\">if</span>(n &gt; <span class=\"hljs-number\">1</span>){\r\n        System.out.println(i + <span class=\"hljs-string\">\",\"</span> + <span class=\"hljs-number\">1</span>);\r\n    }\r\n}\r\n</code></div></pre>', '80', '2021-03-30 22:27:12');
INSERT INTO `question` VALUES ('10', '筛选质数模板', '<h4><a id=\"_0\"></a>埃氏筛法</h4>\n<h5><a id=\"_2\"></a>思路</h5>\n<p>每次把当前 <strong>i</strong> 的倍数筛除，只有质数才会满足 <strong>if</strong> 条件</p>\n<p>只需要筛除质数的倍数即可，因为合数的倍数已经被合数的最小质因子给筛除了</p>\n<h5><a id=\"_8\"></a>注意点</h5>\n<p>质数定理：1到N中，有 N/lnN 个质数</p>\n<p>理论上时间复杂度为O(n)，实际情况为O(nloglogn);</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">int</span> N = <span class=\"hljs-number\">1000001</span>, count = <span class=\"hljs-number\">0</span>;\n<span class=\"hljs-keyword\">int</span>[] primes = <span class=\"hljs-keyword\">new</span> primes[N];\n<span class=\"hljs-keyword\">boolean</span>[] flag = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">boolean</span>[N];\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">get</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> n)</span></span>{\n    <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">2</span>; i &lt;= n; i ++){\n        <span class=\"hljs-keyword\">if</span>(!flag[i]){\n            primes[count ++] = i;\n            <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> j = i + i; j &lt;= n; j += i){\n                flag[j] = <span class=\"hljs-keyword\">true</span>;\n            }\n        }\n    }\n}\n</code></div></pre>\n<h4><a id=\"_30\"></a>线性筛法</h4>\n<h5><a id=\"_32\"></a>思路</h5>\n<p><strong>每一个合数只会被它的最小质因子筛除</strong></p>\n<ol>\n<li>当 for 中if成立时，说明 p[j] 是 i 的最小质因子，所以p[j]也是p[j]  * i 的最小质因子，这样就筛除了p[j] * i</li>\n<li>当 for 中if不成立时，说明 p[j] 小于 i 的最小质因子，所以p[j]是p[j]  * i 的最小质因子，这样同样筛除了p[j] * i</li>\n</ol>\n<h5><a id=\"_39\"></a>注意点</h5>\n<p>第二重的循环中循环条件不需要 j &lt; count 的原因</p>\n<ol>\n<li>当 i 为合数时，必定出现 i % primes[j] == 0，所以会直接退出</li>\n<li>当 i 为质数时，必定出现 i == primes[j]，所以也会在最后一次循环中退出，因为循环之前已经将 i 加入了primes数组</li>\n</ol>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-comment\">//count为质数的数量</span>\n<span class=\"hljs-keyword\">int</span> N = <span class=\"hljs-number\">1000001</span>, count = <span class=\"hljs-number\">0</span>;\n<span class=\"hljs-comment\">//用于装载所有已经被判断出来的质数</span>\n<span class=\"hljs-keyword\">int</span>[] primes = <span class=\"hljs-keyword\">new</span> primes[N];\n<span class=\"hljs-comment\">//判断对应下标是否为合数，合数则赋值为true</span>\n<span class=\"hljs-keyword\">boolean</span>[] flag = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">boolean</span>[N];\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">get</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> n)</span></span>{\n    <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">2</span>; i &lt;= n; i ++){\n        <span class=\"hljs-keyword\">if</span>(!flag[i]){\n            primes[count ++] = i;\n        }\n        <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> j = <span class=\"hljs-number\">0</span>; primes[j] &lt;= n / i; j ++){\n        	flag[primes[j] * i] = <span class=\"hljs-keyword\">true</span>;\n            <span class=\"hljs-keyword\">if</span>(i % primes[j] == <span class=\"hljs-number\">0</span>){\n                <span class=\"hljs-keyword\">break</span>;\n            }\n        }\n    }\n}\n</code></div></pre>', '80', '2021-03-30 22:28:49');
INSERT INTO `question` VALUES ('11', '红与黑（BFS、DFS）', '<h4>来源</h4>\r\n<span>原题链接：https://www.acwing.com/problem/content/1115/</span>\r\n<h4>代码</h4>\r\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">import</span> javafx.util.Pair;\r\n\r\n<span class=\"hljs-keyword\">import</span> java.util.LinkedList;\r\n<span class=\"hljs-keyword\">import</span> java.util.Queue;\r\n<span class=\"hljs-keyword\">import</span> java.util.Scanner;\r\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">day04</span>红与黑 </span>{\r\n    <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">int</span> n,m;\r\n    <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">char</span>[][] arr;\r\n    <span class=\"hljs-comment\">//偏移数组</span>\r\n    <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">int</span>[] dx = {-<span class=\"hljs-number\">1</span>, <span class=\"hljs-number\">0</span>, <span class=\"hljs-number\">1</span>, <span class=\"hljs-number\">0</span>}, dy = {<span class=\"hljs-number\">0</span>, <span class=\"hljs-number\">1</span>, <span class=\"hljs-number\">0</span>, -<span class=\"hljs-number\">1</span>};\r\n    <span class=\"hljs-comment\">//创建队列</span>\r\n    <span class=\"hljs-keyword\">static</span> Queue&lt;Pair&gt; queue = <span class=\"hljs-keyword\">new</span> LinkedList&lt;&gt;();\r\n    <span class=\"hljs-comment\">//配对类</span>\r\n    <span class=\"hljs-keyword\">static</span> Pair&lt;Integer,Integer&gt; pair;\r\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span> </span>{\r\n        Scanner sc = <span class=\"hljs-keyword\">new</span> Scanner(System.in);\r\n        <span class=\"hljs-keyword\">while</span> (<span class=\"hljs-keyword\">true</span>){\r\n            <span class=\"hljs-comment\">//读入行列</span>\r\n            m = sc.nextInt();\r\n            n = sc.nextInt();\r\n            <span class=\"hljs-keyword\">if</span> (m == <span class=\"hljs-number\">0</span> || n == <span class=\"hljs-number\">0</span>) {\r\n                <span class=\"hljs-keyword\">break</span>;\r\n            }\r\n            <span class=\"hljs-comment\">//存储地图</span>\r\n            arr = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">char</span>[<span class=\"hljs-number\">20</span>][<span class=\"hljs-number\">20</span>];\r\n            <span class=\"hljs-comment\">//存储起点位置</span>\r\n            <span class=\"hljs-keyword\">int</span> x = <span class=\"hljs-number\">0</span>,y = <span class=\"hljs-number\">0</span>;\r\n            <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>; i &lt; n; i ++){\r\n                <span class=\"hljs-comment\">//获得每行的字符串，然后分割赋值给数组</span>\r\n                String str= sc.next();\r\n                <span class=\"hljs-keyword\">for</span> (<span class=\"hljs-keyword\">int</span> j = <span class=\"hljs-number\">0</span>; j &lt; m; j ++){\r\n                    arr[i][j] = str.charAt(j);\r\n                    <span class=\"hljs-comment\">//寻找起点位置</span>\r\n                    <span class=\"hljs-keyword\">if</span>(arr[i][j] == <span class=\"hljs-string\">\'@\'</span>){\r\n                        x = i;\r\n                        y = j;\r\n                        <span class=\"hljs-comment\">//起点被标记为障碍物</span>\r\n                        arr[i][j] = <span class=\"hljs-string\">\'#\'</span>;\r\n                        <span class=\"hljs-comment\">//把起点位置加入队列</span>\r\n                        pair = <span class=\"hljs-keyword\">new</span> Pair&lt;&gt;(x,y);\r\n                        queue.offer(pair);\r\n                    }\r\n                }\r\n            }\r\n            System.out.println(bfs(x,y));\r\n        }\r\n    }\r\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">int</span> <span class=\"hljs-title\">bfs</span><span class=\"hljs-params\">(<span class=\"hljs-keyword\">int</span> x,<span class=\"hljs-keyword\">int</span> y)</span></span>{\r\n        <span class=\"hljs-keyword\">int</span> res = <span class=\"hljs-number\">0</span>;\r\n        <span class=\"hljs-keyword\">while</span>(!queue.isEmpty()){\r\n            <span class=\"hljs-comment\">//弹出队头</span>\r\n            Pair&lt;Integer,Integer&gt; t = queue.poll();\r\n            <span class=\"hljs-comment\">//往四周扩散</span>\r\n            <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">0</span>; i &lt; <span class=\"hljs-number\">4</span>; i ++){\r\n                <span class=\"hljs-keyword\">int</span> sx = t.getKey() + dx[i];\r\n                <span class=\"hljs-keyword\">int</span> sy = t.getValue() + dy[i];\r\n                <span class=\"hljs-comment\">//判断偏移后是否越界或者已走过，越界则直接退出</span>\r\n                <span class=\"hljs-keyword\">if</span> (sx &lt; <span class=\"hljs-number\">0</span> || sx &gt;= n || sy &lt; <span class=\"hljs-number\">0</span> || sy &gt;=m || arr[sx][sy] != <span class=\"hljs-string\">\'.\'</span>){\r\n                    <span class=\"hljs-keyword\">continue</span>;\r\n                }\r\n                <span class=\"hljs-comment\">//没有越界则判断是否可行</span>\r\n                <span class=\"hljs-keyword\">if</span> (arr[sx][sy] == <span class=\"hljs-string\">\'.\'</span>){\r\n                    res ++;\r\n                    arr[sx][sy] = <span class=\"hljs-string\">\'#\'</span>;\r\n                    <span class=\"hljs-comment\">//把当前元素配对加入队列</span>\r\n                    pair = <span class=\"hljs-keyword\">new</span> Pair&lt;&gt;(sx,sy);\r\n                    queue.offer(pair);\r\n                }\r\n            }\r\n        }\r\n        <span class=\"hljs-keyword\">return</span> res;\r\n    }\r\n}\r\n</code></div></pre>', '80', '2021-03-30 22:32:43');
INSERT INTO `question` VALUES ('12', '开心的金明（01背包DP问题）', '<h2><a id=\"_0\"></a>思路</h2>\n<p>可以优化到一维，具体看讲解</p>\n<p>DP分析</p>\n<ul>\n<li>\n<p>状态表示    <strong>f(i,j)</strong></p>\n<ul>\n<li>集合		在不超过总价值<strong>j</strong>的情况下，从前<strong>i</strong>个物品中选出最大价值</li>\n<li>属性        最大价值</li>\n</ul>\n</li>\n<li>\n<p>状态计算</p>\n<p>对于 <strong>f(i,j)</strong>，有两种情况</p>\n<p>一、不选第<strong>i</strong>个物品</p>\n<p>则 <strong>f(i,j)</strong>  ==  <strong>f(i - 1,j)</strong></p>\n<p>二、选第<strong>i</strong>个物品</p>\n<p>由于已经确定了选i个物品，则价值的一部分已经被确定</p>\n<p>设<strong>v</strong>为第<strong>i</strong>个物品的价值，<strong>w</strong>为第<strong>i</strong>个物品的价值与重要度的乘积</p>\n<p>可得此时 <strong>f(i,j)</strong>  ==  <strong>f(i - 1,j - v)</strong> + <strong>w</strong></p>\n</li>\n</ul>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-comment\">/*\n    原题链接：https://www.acwing.com/problem/content/428/\n*/</span>\n<span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span></span>{\n        Scanner sc = <span class=\"hljs-keyword\">new</span> Scanner(System.in);\n        <span class=\"hljs-keyword\">int</span> n = sc.nextInt();\n        <span class=\"hljs-keyword\">int</span> m = sc.nextInt();\n        <span class=\"hljs-keyword\">int</span>[][] price = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">int</span>[m + <span class=\"hljs-number\">1</span>][<span class=\"hljs-number\">2</span>];\n        <span class=\"hljs-keyword\">int</span>[][] dp = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-keyword\">int</span>[m + <span class=\"hljs-number\">1</span>][n + <span class=\"hljs-number\">1</span>];\n        <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> i = <span class=\"hljs-number\">1</span>; i &lt;= m; i ++){\n            <span class=\"hljs-comment\">//注意price数组可以优化为以下</span>\n            <span class=\"hljs-comment\">//int p1,p2;        //物品的体积与价值  每次输入进来时都会被覆盖，所以可以这样</span>\n            <span class=\"hljs-comment\">//int v = p1 * p2;  //乘积</span>\n            price[i][<span class=\"hljs-number\">0</span>] = sc.nextInt();\n            price[i][<span class=\"hljs-number\">1</span>] = sc.nextInt();\n            <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">int</span> j = <span class=\"hljs-number\">1</span>; j &lt;= n; j++){\n                dp[i][j] = dp[i - <span class=\"hljs-number\">1</span>][j];\n                <span class=\"hljs-keyword\">if</span>(j &gt;= price[i][<span class=\"hljs-number\">0</span>]){\n                    dp[i][j] = Math.max( dp[i][j] , dp[i - <span class=\"hljs-number\">1</span>][j - price[i][<span class=\"hljs-number\">0</span>]] + price[i][<span class=\"hljs-number\">0</span>] * price[i][<span class=\"hljs-number\">1</span>] );\n                }\n            }\n        }\n        System.out.println(dp[m][n]);\n    }\n</code></div></pre>\n', '80', '2021-03-31 00:05:57');
INSERT INTO `question` VALUES ('13', '888', '<p>78</p>\n', '106', '2021-04-02 12:15:51');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique-username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('80', 'lisi', '$2a$10$/6te/Ctl9SK5VzkO4zxHk.wnV892OkgmtJJUFJ388qtulp8EBN2Cu', '1467965694@qq.com');
INSERT INTO `user` VALUES ('84', '4687', '$2a$10$E0m1k45YfR9wSBkM7lBSv.ajJS6Je5wfgJCZRHRfwrx/iEnt9r4RC', '1467965694@qq.com');
INSERT INTO `user` VALUES ('91', 'lisi9', '$2a$10$ZJ1J/2d8ictidKj4nhShTO16h6VBleP3CAEIhCFbHZEY9Xwag3zne', '1467965694@qq.com');
INSERT INTO `user` VALUES ('93', 'lisi8', '$2a$10$vgDjv.gg1DTCHgXYteXIO.5V1IdoyCwrHlx7iWLeTkhvGhE.brBxe', '1467965694@qq.com');
INSERT INTO `user` VALUES ('94', '456', '$2a$10$9OoLRqR17B4lCU7xnm1d..pmgxb8uQ29dTqQ3qgsJGms7v.m21hMq', '1467965694@qq.com');
INSERT INTO `user` VALUES ('95', '456456', '$2a$10$1jwlqBqGYQrI0uz6XcOM1eMjsnWUkRQ/Zu7j6xBg7vL2ibf3Hfy3O', '1467965694@qq.com');
INSERT INTO `user` VALUES ('98', '74186', '$2a$10$nJ5N9KGOZoMLfErLKeJOmegICfBBK6gZ/rahZ4s7VUAbqDmh2hqg.', '1467965694@qq.com');
INSERT INTO `user` VALUES ('99', '7197419749', '$2a$10$StsjZNlWJxc2qA8Zm37gT.FeBjNKgpBc4WAs2.mwO6yPvAe7RMEsO', '1467965694@qq.com');
INSERT INTO `user` VALUES ('100', '418989', '$2a$10$JmknouiIB1.j6d9m0Rs/0up6b3jcssymA/Bb1O9uehrprCk7AhwJe', '1467965694@qq.com');
INSERT INTO `user` VALUES ('102', '18648', '$2a$10$gzTn0zGOydzr4NzCJLVvJeUGBFqFRSYMZRgh.0vzeqg984ZvSg1Cy', '1467965694@qq.com');
INSERT INTO `user` VALUES ('105', '1478', '$2a$10$zRsHHaGMgMsdnAw6chdiB.xWzJlianiFNvwaKPu.v91/Nnf1EbnfG', '1467965694@qq.com');
INSERT INTO `user` VALUES ('106', 'misaki', '$2a$10$7vnPQZ2LYbxWH8XfhafZl.TCusA.41bUCOlJwptggMh8GuktwqDwa', '1939295014@qq.com');
INSERT INTO `user` VALUES ('107', '王五', '$2a$10$pIFRFL.x3dVKDNeBDUtBHuVtvT8F2XnxzHoS/GEG9vdOuf37NnvQu', '1467965694@qq.com');
