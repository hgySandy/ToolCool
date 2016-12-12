package com.example.ljy.model;

import java.util.List;

/**
 * Created by LJY on 2016/12/11.
 */
public class UpdateLogBean {
    /**
     * success : true
     * items : [{"version":90,"name":"3.0.2","date":"2016-10-25","log":"1、修复上个版本带来的一些机型正文页加载代码片段慢的问题，请务必升级。\n2、周刊里推荐的活动显示活动时间和地点。\n3、修复翻页时不能调音量键的bug。\n4、个人设置页支持社交账号的取消关联。\n5、经网友指正，更改了缓存的图片数据路径到/Android/data/下(所以之前缓存的图片会丢失)，在此表示感谢。\n"},{"version":89,"name":"3.0.1","date":"2016-10-14","log":"1、修复站点订阅后状态显示可能错误的bug。\n2、修复音量键翻页设置不起作用的bug。\n3、正文页排版优化，支持代码片段语法高亮。\n4、文章列表页做了风格调整。\n5、正文页右上角菜单和分享里新增复制链接功能。\n6、修复头像裁剪在安卓6以上版本可能没有效果的bug。\n7、一些UI细节的改进。\n"},{"version":88,"name":"3.0.0","date":"2016-09-14","log":"1、聚合阅读终于来了。主题和站点订阅频道全面支持聚合阅读，亲可以对订阅的主题和站点进行自定义分组，通过聚合阅读一页式快速浏览。对于主题聚合阅读，系统会自动合并交集的主题内容。对于站点聚合阅读，系统会自动过滤相似文章，如果你定义的分组中同类新闻站点居多，可能就会收到过滤条数的提示啦。\n2、新版将不再支持未登录时订阅主题和站点，旧版本用户如果本地有订阅数据，烦请登录后再订阅一遍了。\n3、解决文章列表页下拉时图片会闪烁的问题，并提升显示性能。\n4、大范围解决页面OverDraw问题，整体提升应用性能。\n5、提升正文页加载速度。如果亲在使用中正文页有显示问题，可以反馈给我们。\n6、根据反馈优化搜索结果页等细节，不一一表述。\n"},{"version":87,"name":"2.5.3","date":"2016-07-27","log":"1、继续改进正文排版，如果你有遇到排版或是其他方面有问题的文章，欢迎点击正文页右上角的文章纠错反馈给我们。\n2、修复调整字号后正文页显示可能不正常的bug。\n3、文章分享做了一些优化。\n4、根据反馈对一些细节做了改进。\n"},{"version":86,"name":"2.5.2","date":"2016-06-17","log":"1、修复分享到朋友圈没有链接的bug。\n2、正文页大图显示效果的优化。\n3、文章搜索筛选效果的改进。\n4、UI和代码上的一些细节优化。\n"},{"version":85,"name":"2.5.1","date":"2016-06-09","log":"1、支持推刊迁移和删除。\n2、支持评论的删除。\n3、支持对pocket、有道的分享，改进了分享组件实现方式。\n4、对反馈的排版等问题做了改进。\n"},{"version":85,"name":"2.5.1","date":"2016-06-09","log":"1、支持推刊迁移和删除。\n2、支持评论的删除。\n3、支持对pocket、有道的分享，改进了分享组件实现方式。\n4、对反馈的排版等问题做了改进。\n"},{"version":84,"name":"2.5.0","date":"2016-05-11","log":"1、新增消息通知功能，目前有系统通知、活动通知和评论通知，在\u201c我的\u201d页面中可以看到。\n2、修复搜索提示可能没有被记录的bug。\n3、修复文章纠错、评论输入等页面在部分系统上文字颜色默认为白色的问题。\n4、根据反馈对一些细节做了改进，修复一些特殊情况下有闪退的bug。\n"},{"version":82,"name":"2.4.2","date":"2016-04-3","log":"1、修复部分用户点击\u201c更多设置\u201d闪退的bug。\n2、修复正文页重新载入后可能空白页的bug。\n3、更多分享中新增了几个分享源，如果你分享时没有找到想要的分享源，可以反馈给我们。\n4、针对反馈做了一些细节上的改进调整。\n"},{"version":80,"name":"2.4.0","date":"2016-03-25","log":"1、代码升级到最新版本的官方支持库，抽屉菜单采用官方风格。重写了部分代码，如果你在使用中遇到bug，欢迎反馈给我们。\n2、站点和主题抽离到抽屉菜单，但其实，我们在下一版可能会采用底部Tab。\n3、抽屉菜单增加个人信息页，包括编辑个人信息、收藏、推刊、待读等。\n4、去掉第三方推送，避免占用资源和不兼容问题。\n5、正文页采用硬件加速，提高流畅度。如果你有遇到因此出现空白页的情况，请将系统信息反馈给我们。\n6、文章搜索支持清除搜索记录。\n7、修复订阅的周刊重复提醒的问题。\n8、根据反馈对一些细节做了改进调整，不一一表述。\n"},{"version":79,"name":"2.3.2","date":"2016-02-03","log":"1、修复部分系统存在的正文页重入后白屏的问题。\n2、启动页支持远程下载图片，一周里每天一张启动图。\n3、合并周刊和一周拾遗检测的请求，减少网络请求数。\n4、根据反馈对交互的一些细节做了改进。\n"},{"version":77,"name":"2.3.0","date":"2015-01-26","log":"1、新增周刊频道，目前网站提供的科技周刊、创业周刊、设计匠艺、编程狂人和Guru Weekly通通集成到APP上，更方便阅读。\n2、文章搜索支持搜索历史纪录并对加载做了优化。\n3、新的新用户引导页面。\n4、用户信息页支持社交账号的绑定.\n5、正文排版做了优化，代码片段可以随字号同步调整大小。\n6、更新了微信分享组件，如果你仍遇到不能分享到微信，请将机型和系统信息反馈给我们。\n7、针对反馈对能修复或调整的细节都做了改进，欢迎继续反馈以帮助我们改进。\n"},{"version":76,"name":"2.2.2","date":"2015-12-23","log":"1、左侧菜单新的样式风格。 \n2、修复搜索点击返回错误页面的bug。 \n3、针对反馈对一些细节做了优化调整。 \n4、根据错误日志修复了一些机型上可能出现的问题。如果你在使用过程中遇到闪退等重大bug，欢迎发邮件反馈给我们。\n"},{"version":74,"name":"2.2.1","date":"2015-12-03","log":"1、主题文章列表页支持热门筛选(状态会被保存)\n2、采用了新的启动页（暂时只有两张背景图，后续会支持远程下载）。\n3、修复分享到QQ空间、QQ、印象笔记可能失败的bug（如果你在分享时还有问题，请说明手机型号和系统反馈给我们）。\n4、优化了社交账号授权过程，避免重复点击。\n5、针对反馈，解决了搜索页面点击两次才能返回、首页频道拖动存在bug、登录提交后隐藏键盘等问题。\n"},{"version":73,"name":"2.2.0","date":"2015-10-28","log":"1、首页改版，主题和站点合并到首页，登录后支持频道的自定义。\n2、修复列表页请求失败后不能再次下拉刷新的bug。\n3、调整正文页配色方案和修复部分机型长按正文后头部错位的问题。\n"},{"version":72,"name":"2.1.3","date":"2015-09-17","log":"1、正文页改版，显示相关主题，默认使用多彩版风格，可在背景设置中修改风格。\n2、列表页和正文页，点击actionbar可快速返回顶部。\n3、修复文章搜索因为语言选项遮挡条目的bug。\n4、替换新的加载更多组件。\n5、修复待读文章滑动删除可能产生的偏差效果。\n"},{"version":71,"name":"2.1.2","date":"2015-08-30","log":"1、在连续多个请求失败后，自动进行服务端状态检测。\n2、修复文章中播放视频退出时继续播放的bug。\n3、修复正文页音量键在非翻页状态下不能调声的bug。\n4、针对大家的反馈做了多项细节方面的调整优化。\n"},{"version":70,"name":"2.1.1","date":"2015-08-14","log":"1、修复部分机型可能出现正文排版错乱的bug。\n2、调整文章正文加载方式，优化显示效果。\n3、热门站点切换语言时列表自动调整到头部。\n4、文章列表中增加\u201c荐\u201d\u201c热\u201d标记等一些UI方面的改进。\n"},{"version":69,"name":"2.1.0","date":"2015-08-08","log":"1、离线下载改版，系统自动分析订阅频道更新情况和你的阅读情况，对频道进行分类。\n2、首页文章分类Tab和搜索页面Tab更换实现方式，切换效果更萌萌的啦。\n3、文章正文新增了赞功能并将工具栏从底部挪到顶部，配色等方面也进行了调整。\n4、支持邮箱注册。\n5、使用最新MD的下拉刷新方式。\n6、文章搜索功能从左侧菜单挪到首页顶部。\n7、夜间模式、Switch按钮等一些UI细节的调整和改进。\n"},{"version":67,"name":"2.0.6","date":"2015-07-17","log":"0、针对魅族手机的正文页单独做了布局调整\n1、更多设置中支持正文外部链接打开方式的设置\n2、新增文章纠错功能\n3、应用启动时自动检测并删除过期的图片\n4、正文标题大小、分享功能等一些细节改进\n"},{"version":66,"name":"2.0.5","date":"2015-07-02","log":"1、支持微信登录。\n2、优化正文页状态栏以及底栏的配色。\n3、修复部分用户遇到的意见反馈输入框被输入法遮住的情况。\n4、支持点击actionbar打开关闭左侧菜单。\n5、支持应用内打开外部链接。\n6、排版、正文渲染、文章列表加载等一些细节改进。\n"},{"version":64,"name":"2.0.4","date":"2015-06-18","log":"1、待读文章支持左滑删除。\n2、改进分享状态提示。\n3、修复评论后计数不变的bug。\n3、用户信息页等一些UI细节改进。\n"},{"version":64,"name":"2.0.3","date":"2015-06-04","log":"1、我的主题支持显示未读数字，订阅的主题支持语言设定。\n2、主题和站点订阅支持我的排序、更新时间和订阅时间三种模式。\n3、支持邮箱、密码等个人信息修改功能。\n4、解决列表页重复点击时可能打开多次页面的情况。\n5、解决更换头像时可能获取旧的头像的问题。\n6、正文页由全屏模式变成显示状态栏的模式（这下能看到时间啦）。\n"},{"version":62,"name":"2.0.2","date":"2015-05-15","log":"1、修复部分页面网络错误时顶部不能回退的bug。 \n2、修复我的主题不能和网站更新数据同步的bug。 \n3、支持微博和QQ直接登录以及改进注册流程。 \n4、改进下拉列表滑动过长的问题。 \n5、修复部分系统搜索有空格时没结果的bug。 \n6、解决有时会出现离线阅读丢失行的bug。 \n7、修复管理订阅修改后返回可能闪退的bug。\n"},{"version":62,"name":"2.0.1","date":"2015-03-30","log":"1、收藏到推刊页面提交方式调整。\n2、Button全面支持Ripple效果。\n3、修复推荐设置页闪退的bug。\n4、UI、icon等一些细节优化调整。\n"},{"version":61,"name":"2.0.0","date":"2015-03-18","log":"大版本升级，敬请亲们升级体验。\n1、 全新的Material Design风格。\n2、调整主题和站点订阅频道的显示风格。\n3、改进夜间模式效果，并将功能提到左侧菜单方便切换。\n4、站点订阅频道支持显示未读数字。\n5、支持4.4以上版本得沉浸式状态栏。\n"},{"version":57,"name":"1.3.7","date":"2015-02-12","log":"1、正文排版优化\n2、修复编辑推刊隐私设置无效的bug\n3、修复设置夜间模式可能闪退等bug\n4、UI细节优化调整\n"},{"version":56,"name":"1.3.6","date":"2015-01-19","log":"1、合并\u201c推荐\u201d和\u201c发现\u201d频道为文章频道\n2、正文页支持更换背景色\n3、支持音量键翻页（单手翻页来啦）\n4、正文排版优化\n5、修复搜索页面可能闪退的bug\n6、修复一周拾遗重复推送的bug\n7、修正离线阅读站点logo可能错误的bug\n"},{"version":54,"name":"1.3.5","date":"2014-12-26","log":"1、离线阅读支持手动删除过期数据(因缓存方式修改，版本升级后缓存的列表将实效，重新下载即可)\n2、解决异步操作并发性的问题，提高响应速度\n3、推荐页菜单加上待读和收藏入口\n4、解决订阅频道因不存在的图片造成图片显示错位的情况\n5、解决文章搜索的语言筛选遮挡\u201d查看更多\u201c的bug\n6、修复安卓5中正文页\u201d查看站点\u201c显示不全的bug\n7、过滤掉更多分享中的重复项\n8、如果禁用\u201d一周拾遗\u201c推送，将关闭pushservice，避免占用系统资源\n"},{"version":54,"name":"1.3.4","date":"2014-11-23","log":"1、修复离线阅读中文章列表加载错误的bug，使用离线的朋友请务必升级。\n2、设置中增加\u201c更新日志\u201d，方便亲了解偶们的开发进展。\n3、修复\u201c一周拾遗\u201d消息icon过大的问题。\n"},{"version":53,"name":"1.3.3","date":"2014-11-11","log":"1、个人中心改版，支持更换头像和用户信息啦。\n2、新的用户引导提示。\n3、UI细节改进。\n"},{"version":0,"name":"1.3.2","date":"2014-10-27","log":"1、文章搜索升级为整合搜索，一个页面整合文章、主题、站点搜索。\n2、优化离线阅读，离线数据独立缓存避免刷新后更新内容。\n3、UI细节调整。\n"},{"version":0,"name":"1.3.1","date":"2014-10-11","log":"0、更好的适配MX4\n1、修复文章搜索的回退页面错误的bug\n2、离线下载中增加待读文章\n3、优化ICON以适应高分辨率手机\n4、页面切换增加过渡动画\n5、改进站点搜索排序方式\n6、一些像素级的UI细节改进\n"},{"version":0,"name":"1.3.0","date":"2014-09-28","log":"1、登录用户的\u201c一周拾遗\u201d来了（可在更多设置中更改推送方式）\n2、正文支持左右滑动翻页（可在更多设置中关闭）\n3、精华推荐来了，适合时间少的朋友\n4、离线下载支持全选\n5、修复离线阅读、搜索结果存在的错位的bug\n6、一些细节改进。\n"},{"version":0,"name":"1.2.9","date":"2014-09-10","log":"1、左侧菜单新增文章搜索功能。\n2、站点和主题频道新增订阅管理功能，更方便的排序删除订阅来了。\n3、站点订阅增加语言分类选择，浏览更便捷。\n4、离线下载支持选择下载数量。\n5、修复离线时收藏和待读的bug。\n6、一些细节的调整和改进。\n"},{"version":0,"name":"1.2.8","date":"2014-08-22","log":"1、迁移接口服务，提升远程访问稳定性。\n2、站点订阅频道新增\u201c新新推荐\u201d。\n3、文章推荐支持设置推荐语言，加入使用说明。\n4、改进夜间模式切换后各页面的首次加载速度。\n5、改进正文点击图片出大图后的放大效果。\n6、针对不同分辨率优化一些图片和icon。\n7、根据反馈对一些细节和bug的修复。\n"},{"version":0,"name":"1.2.7","date":"2014-08-05","log":"1、离线下载升级为离线阅读，左侧菜单一等公民，可以更方面的阅读已下载的内容，你们的吐槽赢了。\n2、调整收藏功能，如果你只有一个默认推刊，可以在更多设置中关闭收藏到推刊弹窗而直接收藏。\n3、主题文章列表页如果切换语言（中英文），本地会记录状态，下次进入仍是该语言的内容。\n4、根据大家的反馈，对一些小细节做了改进，icon啊，清晰度啊，进度条啊，actionbar title不能点击啊等等。\n5、一些使用说明的增加（别说你找不到收藏的入口啊）。\n6、不太好确定的闪退情况的尝试修复。\n"},{"version":0,"name":"1.2.4","date":"2014-07-27","log":"1、改进离线下载加载列表时的阻塞操作，对新用户添加使用说明。\n2、意见反馈夜间模式优化。\n3、登录时本地数据合并到云端的支持。\n4、关于我们、正文页等一些细节改进。\n5、修复一些操作可能造成的闪退的bug。\n"},{"version":0,"name":"1.2.3","date":"2014-07-20","log":"1、修复推荐／热门文章加载更多部分情况下逻辑不正确的bug。\n2、站点订阅改版，订阅更直观方便。\n3、主题订阅改版，和站点风格统一。\n"},{"version":0,"name":"1.2.1","date":"2014-07-10","log":"1、采用新的左侧抽屉组件，重写了好些代码，性能方面也有了一些改进。\n2、改进收藏到推刊相关的功能。\n3、支持注册用户时本地数据同步到云端。\n4、改进亲们反馈的夜间模式、正文排版、离线下载相关的UI细节优化。\n"},{"version":0,"name":"1.2.0","date":"2014-06-25","log":"1、收藏功能改版，登录用户支持收藏到推刊，收藏的文章可以分类啦。\n2、个人主页来啦，整合收藏、推刊、待读内容。\n3、正文排版等细节继续不断改进。\n"},{"version":0,"name":"1.1.10","date":"2014-06-19","log":"1、由于亲们不断的吐槽我们的正文排版很粗糙，我们痛下决心解决该问题。目前来看多数页面的排版有了很大的改观，客户端的样式设置也做了相应变动。\n2、针对魅族的smartbar，做了全面的适配。\n3、优化图片清晰度。\n"},{"version":0,"name":"1.1.9","date":"2014-06-08","log":"1、正文排版优化。\n2、修复分享文章时附图不正确的bug。\n3、解决有时第一次打开设置页慢的问题。\n4、更多设置中新增关闭文章右滑出评论的功能。\n5、更多设置中新增更改文章字号功能。\n6、新用户引导页来了。\n7、夜间模式等UI细节优化。\n"},{"version":0,"name":"1.1.8","date":"2014-05-28","log":"1.修复部分用户登录/注销闪退的bug。\n2.改进图片清晰度。\n3.修复4.2以上系统点击正文图片不出大图的bug。\n4.修复4.4系统正文图片出不来的bug。\n5.修复无sdcard时正文出不来图片的bug。\n6.修复未登陆时我的站点图片不更新的bug。\n7.自动升级策略调整。\n"},{"version":0,"name":"1.1.7","date":"2014-05-19","log":"0、推荐/热门文章引入WIFI时自动下载（可在更多设置中关闭）。\n1、自动更新策略调整，针对渠道进行分包发布，目前的包有通用、魅族专版、google play专版、HD专版（仅小米平板），避免自动更新下载错误的包。\n2、增加忘记密码功能。\n3、正文页优化，对于大屏手机在wifi时加载大图。\n4、更改logo背景为浅灰（之前的白色背景在好些市场看起来好屎）。\n5、没有最好只有更好的诸多细节改进。\n"},{"version":0,"name":"1.1.6","date":"2014-05-09","log":"0、向下兼容至安卓2.1版本。\n1、全面适配小屏（3寸）大屏（7寸以上）。\n2、继续优化性能，解决部分机型因使用组件过于复杂而闪退的情况。\n3、支持记住夜间模式状态。\n4、正文页的\u201c更多\u201d按钮增加添加待读功能。\n5、一些UI细节的改进。\n6、增加分享应用给好友功能。\n"},{"version":0,"name":"1.1.5","date":"2014-04-28","log":"0）热门频道升级为发现频道，新增\u201c最新文章\u201d分类。\n1）解决部分机型因硬件加速造成在\u201c站点\u201d频道闪退的问题。\n2）被很多人吐槽的下拉刷新进度条覆盖左侧抽屉的问题被hack掉啦。\n3）解决列表页非纵向滑动容易触发下滑刷新的问题，左滑待读更灵敏。\n4）解决添加主题/站点后缓存没有更新的bug。\n5）重新打开已读文章自动定位到上次阅读的位置。\n6）解决偶尔存在的加载更多出不来的问题。\n"},{"version":0,"name":"1.1.4","date":"2014-04-20","log":"1、热门文章改版，分类更直接展现。\n2、改进清空图片缓存功能（如头像等系统图片不被删除）。\n3、WIFI时自动下载主题站点列表页的图片。\n4、改进离线下载，下载项排在前面显示，修复无网时不能加载列表等问题。\n5、修复分享设置无网时闪退的bug。\n6、多项UI细节改进，应用性能大幅改进。\n"},{"version":0,"name":"1.1.3","date":"2014-04-03","log":"1、修复部分机型下拉反应不灵敏的问题。\n2、修复主题和站点文章不更新的bug。\n3、支持回复评论。\n4、离线下载可记录下载配置。\n5、UI细节改进。\n"},{"version":0,"name":"1.1.2","date":"2014-03-28","log":"1、初版的评论功能来啦！\n2、支持腾讯微博、QQ帐号登录。\n3、离线下载支持全选以及进度提示的问题修复。\n4、修复Qzone分享错误的问题。\n5、支持站点搜索功能。\n6、修复网友反馈的在收藏、分享设置等方面的细节问题。\n"}]
     */

    private boolean success;
    private List<ItemsBean> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * version : 90
         * name : 3.0.2
         * date : 2016-10-25
         * log : 1、修复上个版本带来的一些机型正文页加载代码片段慢的问题，请务必升级。
         2、周刊里推荐的活动显示活动时间和地点。
         3、修复翻页时不能调音量键的bug。
         4、个人设置页支持社交账号的取消关联。
         5、经网友指正，更改了缓存的图片数据路径到/Android/data/下(所以之前缓存的图片会丢失)，在此表示感谢。

         */

        private int version;
        private String name;
        private String date;
        private String log;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }
    }
}
