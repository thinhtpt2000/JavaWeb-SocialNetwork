USE [master]
GO
/****** Object:  Database [SOCIAL_NETWORK_DB]    Script Date: 30/09/2020 20:42:05 ******/
CREATE DATABASE [SOCIAL_NETWORK_DB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SOCIAL_NETWORK_DB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQL2012EXP\MSSQL\DATA\SOCIAL_NETWORK_DB.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SOCIAL_NETWORK_DB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQL2012EXP\MSSQL\DATA\SOCIAL_NETWORK_DB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SOCIAL_NETWORK_DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET  MULTI_USER 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [SOCIAL_NETWORK_DB]
GO
/****** Object:  Table [dbo].[tblArticles]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblArticles](
	[id] [varchar](8) NOT NULL,
	[title] [nvarchar](150) NULL,
	[description] [nvarchar](1000) NULL,
	[imageLink] [varchar](255) NULL,
	[date] [datetime] NULL,
	[status] [int] NULL,
	[postBy] [varchar](50) NULL,
 CONSTRAINT [PK_Posts] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblArticleStatus]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblArticleStatus](
	[statusId] [int] NOT NULL,
	[statusName] [varchar](10) NULL,
 CONSTRAINT [PK_tblArticleStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblComments]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblComments](
	[commentId] [varchar](8) NOT NULL,
	[text] [nvarchar](250) NULL,
	[articleId] [varchar](8) NULL,
	[userId] [varchar](50) NULL,
	[status] [int] NULL,
	[time] [datetime] NULL,
 CONSTRAINT [PK_Comments] PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblCommentStatus]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCommentStatus](
	[statusId] [int] NOT NULL,
	[name] [varchar](10) NULL,
 CONSTRAINT [PK_tblCommentStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblEventType]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblEventType](
	[typeId] [int] NOT NULL,
	[name] [varchar](20) NULL,
 CONSTRAINT [PK_tblEventType] PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblNotifications]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblNotifications](
	[notificationId] [varchar](8) NOT NULL,
	[eventType] [int] NULL,
	[time] [datetime] NULL,
	[commentId] [varchar](8) NULL,
	[reactionId] [varchar](8) NULL,
 CONSTRAINT [PK_Notifications] PRIMARY KEY CLUSTERED 
(
	[notificationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblReactions]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblReactions](
	[reactionId] [varchar](8) NOT NULL,
	[articleId] [varchar](8) NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[typeId] [int] NULL,
	[time] [datetime] NULL,
 CONSTRAINT [PK_Reactions] PRIMARY KEY CLUSTERED 
(
	[reactionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblReactionTypes]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblReactionTypes](
	[typeId] [int] NOT NULL,
	[name] [varchar](20) NULL,
 CONSTRAINT [PK_tblReactionTypes] PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusId] [int] NOT NULL,
	[statusName] [varchar](10) NULL,
 CONSTRAINT [PK_tblStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[email] [varchar](50) NOT NULL,
	[name] [nvarchar](50) NULL,
	[password] [varchar](64) NULL,
	[statusId] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblVerifyUser]    Script Date: 30/09/2020 20:42:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblVerifyUser](
	[id] [varchar](8) NOT NULL,
	[email] [varchar](50) NULL,
	[code] [varchar](6) NULL,
	[requestTime] [datetime] NULL,
	[expireTime] [datetime] NULL,
 CONSTRAINT [PK_tblVerifyUser] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'2Wyj2FV2', N'Hôm nay trời mưa...', N'Chiều nay đi học trời mưa to quá...
Các bạn có bị ướt không nè :)))
P/s: ảnh mạng thôi nhé :)', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601302012/social-network-images-upload/v3ikyhc4badioslmab5f.jpg', CAST(N'2020-09-28 21:07:00.943' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'2yP0Njvq', N'Kiểm tra nào!', N'Viết gì vào đây cho nhiêu đây?', N'', CAST(N'2020-09-28 11:22:52.703' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'7rgcgy5t', N'C# - Overview', N'C# is a modern, general-purpose, object-oriented programming language developed by Microsoft and approved by European Computer Manufacturers Association (ECMA) and International Standards Organization (ISO).

C# was developed by Anders Hejlsberg and his team during the development of .Net Framework.

C# is designed for Common Language Infrastructure (CLI), which consists of the executable code and runtime environment that allows use of various high-level languages on different computer platforms and architectures.

Although C# constructs closely follow traditional high-level languages, C and C++ and being an object-oriented programming language. It has strong resemblance with Java, it has numerous strong programming features that make it endearing to a number of programmers worldwide.

From TutorialsPoint', N'', CAST(N'2020-09-30 19:42:57.303' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'95fpVe65', N'Music for today ?', N'Hôm nay trời mưa nên cũng khá mát. Làm gì để tận hưởng không khí này đây? Ăn miếng bánh trung thu, uống 1 tách trà nóng và thưởng thức 1 vài bài nhạc để "deep" cùng mưa nè. Nếu bạn là 1 fan của nhạc Hoa, đừng bỏ qua list nhạc này nhé ! ^ ^
Nhớ em -Ngải Thần https://www.youtube.com/watch?v=zXkNjhJU4mo   
Người tôi yêu - Lâm Thu Văn https://www.youtube.com/watch?v=6XouWKPvdag    
Diễn viên - Tiết Chi Khiêm https://www.youtube.com/watch?v=pMxMXYr_DRg    
Mashup Tuyết chân thật + Kẻ xấu xí + Người mẫu + Quý ông + Bố mẹ + Diễn viên  
 https://www.youtube.com/watch?v=JZnw8KYMM6o   
', N'', CAST(N'2020-09-28 21:38:30.400' AS DateTime), 0, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'bIEfRaV6', N'Bài đầu tiên', N'Huhu, tui nên viết cái gì đây?', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601266881/social-network-images-upload/gvgj9q4nkpvw1wpkplx6.png', CAST(N'2020-09-28 11:21:32.613' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'BIKvk8Dd', N'Spring Boot - Introduction', N'Spring Boot is an open source Java-based framework used to create a Micro Service. It is developed by Pivotal Team. It is easy to create a stand-alone and production ready spring applications using Spring Boot. Spring Boot contains a comprehensive infrastructure support for developing a micro service and enables you to develop enterprise-ready applications that you can “just run”.
This tutorial is designed for Java developers to understand and develop production-ready spring applications with minimum configurations. It explores major features of Spring Boot such as Starters, Auto-configuration, Beans, Actuator and more. By the end of this tutorial, you will gain an intermediate level of expertise in Spring Boot.
<a href="https://www.tutorialspoint.com/spring_boot/index.htm" target="_blank">See more in Tutorials Point</a>', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601470178/social-network-images-upload/wocjpgk999kd7gc1m0be.jpg', CAST(N'2020-09-30 19:49:44.470' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'CoSpV4Hh', N'a', N'a', N'', CAST(N'2020-09-29 21:16:15.637' AS DateTime), 0, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'dSNJOQVF', N'Kiểm tra kí tự đặc biệt', N'Ahihi, dưới đây là một số kí tự đặc biệt cần lưu ý:
%
_
^
\
/
[
]
', N'', CAST(N'2020-09-30 20:01:55.787' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'e2ptLLDH', N'Bài post này của 1 ai đó ', N'涅炎 - Niết Viêm
Niết bàn và viêm hoả đóoo', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601437953/social-network-images-upload/na2jnajr0cspfitvoozl.jpg', CAST(N'2020-09-30 10:53:13.287' AS DateTime), 1, N'nghiadhse140362@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'GZWRvgFC', N'Chào buổi chiều!', N'Good afternoon!', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601456618/social-network-images-upload/eeedc54icwt1mgvgc06r.jpg', CAST(N'2020-09-30 16:03:44.397' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'HAW0j3Dl', N'Logo của tui', N'Tui cũng vừa làm xong cái logo nè!', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601267921/social-network-images-upload/fnhesxpfwsj4krvzj8dr.png', CAST(N'2020-09-28 11:39:51.317' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'hZj4CDHH', N'Bài hát này cứ vang lại trong đầu tôi!', N'Tôi cứ nghe đi nghe mãi 1 bài hát từ hôm qua đến giờ, nhẩm nhẩm từng lời rap, liệu bài hát này có quá catchy ?? https://www.youtube.com/watch?v=Mx0FRw66ir0&t=37s', N'', CAST(N'2020-09-28 21:24:40.640' AS DateTime), 0, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'I5DF6Gnx', N'Hello?!', N'Mọi người đã thức dậy chưa nhỉ?', N'', CAST(N'2020-09-30 09:38:51.730' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'ibTvTFgZ', N'Tạo Blog đơn giản với Hugo và Github', N'Hướng dẫn tạo blog đơn giản mà không cần biết về HTML, CSS và JS… Bài viết này tui hướng dẫn khá chi tiết đấy nhé! 😉
<a href="https://codecungtui.github.io/tao-blog-don-gian-voi-hugo-va-github/" target="_blank">Xem chi tiết tại đây</a>', N'', CAST(N'2020-09-30 19:52:10.677' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'IHj7QQ4G', N'test giùm bạn Thịnh', N'websiteはきれいです！　５５５５５５５５', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601362744/social-network-images-upload/yftzcgujvjmyahoz5yc6.png', CAST(N'2020-09-29 14:01:59.590' AS DateTime), 1, N'huy.trnminh@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'Jk1o5NPe', N'Chào buổi sáng', N'Chúc mọi người một ngày vui vẻ :v', N'', CAST(N'2020-09-30 09:38:31.843' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'KHFco2AM', N'Xin chào mọi người!', N'Mình vừa tạo account...
Ở đây có gì vui không nhỉ?', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601470805/social-network-images-upload/vyjuoqqncbdwxjini2pt.jpg', CAST(N'2020-09-30 20:00:08.513' AS DateTime), 1, N'thinhtpt.2000@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'Khx9iS3u', N'Trích thơ của TLinh- Rap Việt - Team Suboi', N'Nàng cười rạng rỡ nàng thơ
Ngàn hoa đang nở, nằm mơ về nàng
', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601301979/social-network-images-upload/qp4fuelvxnsc8y4gmvpz.jpg', CAST(N'2020-09-28 21:06:45.437' AS DateTime), 1, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'KRnhfVvE', N'aaaaaaaaaaaaaaaaaaa', N'aaaaaaaaaaaaaaaaaaaaaaaaaaa', N'', CAST(N'2020-09-29 08:34:42.317' AS DateTime), 0, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'nDx0SpHW', N'Music For Today #2', N'Hôm nay trời mưa nên cũng khá mát. Làm gì để tận hưởng không khí này đây? Ăn miếng bánh trung thu, uống 1 tách trà nóng và thưởng thức 1 vài bài nhạc để "deep" cùng mưa nè. Nếu bạn là 1 fan của nhạc Hoa, đừng bỏ qua list nhạc này nhé ! ^ ^
Nhớ em -Ngải Thần <a href="https://www.youtube.com/watch?v=zXkNjhJU4mo"/>
Người tôi yêu - Lâm Thu Văn <a href="https://www.youtube.com/watch?v=6XouWKPvdag"/>
Diễn viên - Tiết Chi Khiêm <a href="https://www.youtube.com/watch?v=pMxMXYr_DRg"/>
Mashup Tuyết chân thật + Kẻ xấu xí + Người mẫu + Quý ông + Bố mẹ + Diễn viên
<a href="https://www.youtube.com/watch?v=JZnw8KYMM6o"/>', N'', CAST(N'2020-09-28 21:41:33.070' AS DateTime), 0, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'NoUo3qyu', N'Lập Trình Viên Cần Biết: Nguyên Tắc Tối Ưu Hiệu Suất - 20% nổ lực để được 80% kết quả', N'1. Nguyên tắc này có thể bao hàm tất cả các nhận định sau:
- 20% công nhân tạo ra 80% kết quả
- 20% khách hàng đóng góp vào 80% doanh thu
- 20% khiếm khuyết gây ra 80% sự cố
- 20% tính năng tạo ra 80% nhu cầu sử dụng
2. Khi nào thì áp dụng Nguyên tắc Pareto (80/20) trong lập trình:
- Chọn Khi Nào Làm Việc
- Khi chọn các tính năng
- Khi sắp xếp danh sách việc cần làm
- Khi bắt đầu một dự án
- Khi học một ngôn ngữ lập trình mới
- Khi gỡ lỗi
- Khi chọn một ý tưởng', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601305353/social-network-images-upload/ryag7belj0xrevekdyhq.jpg', CAST(N'2020-09-28 22:02:37.853' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'NZvyp9of', N'Good evening!', N'Hôm nay của bạn như thế nào?
Cùng chia sẻ...
😀 😃', N'', CAST(N'2020-09-29 21:21:31.110' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'oRx1udvD', N'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\aaaaaaaaaaaaaaaaaaaaaaaaaa', N'aaaaaaaaaaaaaaaaaaaaaaa', N'', CAST(N'2020-09-28 21:17:46.257' AS DateTime), 0, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'OzVz7Of0', N'Mai ai đi chơi Trung Thu không?', N'Tui cô đơn quá, mai ai đi chơi với tui hông?
😢😢😢', N'', CAST(N'2020-09-30 20:36:21.940' AS DateTime), 1, N'thinhtpt.2000@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'rZuiblxp', N'Music Of The Day Part 2', N'Hôm nay trời mát, mát đến nỗi làm con người ta nhớ về Đà Lạt. Không khí trong lành, mát mẻ và mang lại cho ta cảm giác thoải mái. Ngoài ra, nghe đến từ Đà Lạt, người ta lại nghĩ đến sự lãng mạn của Hồ Xuân Hương, sự hùng vĩ của núi rừng, của những con đèo khúc khuỷu đầy dốc. Nghĩ tới mà muốn đi, chắc hết chuyên ngành này đi 1 chuyến quá ta...<br/>
Quay lại chủ đề nào, hôm nay mình nghe rap nhỉ? Những bài rap không phải là những bài để dập xập xình mà là những bài để chill. Hãy quên đi những vất vả và dừng một lát để chill cùng list nhạc này nhé</br>
Thưởng thức âm thanh núi rừng cùng với sự hoang dại của lời rap: 
<a href="https://www.youtube.com/watch?v=zEWSSod0zTY"> Ghé qua - Dick, Tofu, PC</a><br/>
Thưởng thức sự chill chill của nhạc Đen Vâu:<br/> 
<a href="https://www.youtube.com/watch?v=KKc_RMln5UY">Lối nhỏ - Đen</a><br/>
"Anh sống giữa lòng thành phố nhưng lại mơ về thị trấn hoang": <br/>
<a href="https://www.youtube.com/watch?v=L0NZW6pgSLc">Mười năm - Đen</a><br/>
', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601472060/social-network-images-upload/vlkpc78krf9mof7rmsov.jpg', CAST(N'2020-09-30 20:21:04.717' AS DateTime), 1, N'beiuonepiece999@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'Sd5FdD14', N'Music For Today', N'Hôm nay trời mưa nên cũng khá mát. Làm gì để tận hưởng không khí này đây? Ăn miếng bánh trung thu, uống 1 tách trà nóng và thưởng thức 1 vài bài nhạc để "deep" cùng mưa nè. Nếu bạn là 1 fan của nhạc Hoa, đừng bỏ qua list nhạc này nhé ! ^ ^<br/>
<a href="https://www.youtube.com/watch?v=zXkNjhJU4mo" target="_blank">Nhớ em -Ngải Thần </a><br/>
<a href="https://www.youtube.com/watch?v=6XouWKPvdag" target="_blank">Người tôi yêu - Lâm Thu Văn</a><br/>
<a href="https://www.youtube.com/watch?v=pMxMXYr_DRg" target="_blank">Diễn viên - Tiết Chi Khiêm</a><br/>
<a href="https://www.youtube.com/watch?v=JZnw8KYMM6o" target="_blank">Mashup Tuyết chân thật + Kẻ xấu xí + Người mẫu + Quý ông + Bố mẹ + Diễn viên</a><br/>
Chúc các bạn có 1 ngày nghe nhạc vui vẻ!', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601304765/social-network-images-upload/dnngojd8yrecmacfl1yl.jpg', CAST(N'2020-09-28 21:52:50.520' AS DateTime), 1, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'sqiXNlKu', N'History Of C# Programming Language', N'Let’s go look at the change log of the C# programming languages from 1.0 to latest one 7.0 versions. For more details, please see the image given below.
According to the image given above, you can understand which version came in which year. Now, I am going to show you C# version with .NET framework and CLR version with Visual Studio.
C# 1.0.NET framework 1.0,1.1 , CLR version 1.0, Visual Studio 2002.
C# 2.0 .NET framework 2.0 , CLR version 2.0, Visual Studio 2005.
C# 3.0 .NET framework 3.0,3.5 , CLR version 2.0, Visual Studio 2008.
C# 4.0 .NET framework 4.0 , CLR version 4.0, Visual Studio 2010.
C# 5.0 .NET framework 4.5 , CLR version 4.0, Visual Studio 2012,2013.
C# 6.0 .NET framework 4.6 , CLR version 4.0 ,Visual Studio 2013,2015.
C# 7.0 .NET framework 4.6,4.6.1,4.6.2 , CLR version 4.0, Visual Studio 2015, 2017 RC. 
<a href="https://www.c-sharpcorner.com/blogs/history-of-c-sharp-programming-language" target="_blank">References  here</a>', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601470547/social-network-images-upload/y3mx51ubztmnjdcvrg5u.png', CAST(N'2020-09-30 19:55:52.500' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'sQTFmi0O', N'Buổi trưa vui vẻ!', N'こんにちは！
ランチ を たべましたか。', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601349408/social-network-images-upload/psohevvb7wl2kuoxwdwv.jpg', CAST(N'2020-09-29 10:20:04.720' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N't0UqQord', N'aaaaaaaaaaaaaaa', N'aaaaaaaaaaaaaaaaaa', N'', CAST(N'2020-09-30 16:04:38.477' AS DateTime), 0, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'TJgO13Rc', N'Bài post thứ 2 của tui', N'Bài viết của tui chi tiết không nè?', N'', CAST(N'2020-09-28 11:32:26.687' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'vyReuYtb', N'a', N'a', N'', CAST(N'2020-09-29 21:12:37.157' AS DateTime), 0, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'wIQEejyy', N'Chia sẻ tài liệu', N'Tổng hợp tài liệu chia sẻ về việc tự học HTML và CSS cơ bản
<a href="https://laptrinhit.net/tai-lieu-tu-hoc-html5-va-css3-tieng-viet-pdf-co-ban-de-hoc-nhat/" target="_blank">Vào đây để xem nhé!</a>', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601471894/social-network-images-upload/tkabfaamnwtdkuoaupqz.png', CAST(N'2020-09-30 20:18:17.303' AS DateTime), 1, N'thinhtptse140092@fpt.edu.vn')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'X783niDH', N'Post này viết để xoá ', N'Alo 1234', N'', CAST(N'2020-09-30 20:21:47.000' AS DateTime), 0, N'beiuonepiece999@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'YCVFwO12', N'Test special chars in SQL', N'Here is some special chars:
%
_
^
[
]
\
Any more? "%_^[]\"', N'', CAST(N'2020-09-29 21:17:59.190' AS DateTime), 1, N'tthinh2128@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'yHBpIYdT', N'Cả nhà buổi tối vui vẻ!', N'Spam tí gì đi mọi người ơi?!
Có ai online không nhỉ? Cmt đi...', N'', CAST(N'2020-09-30 20:40:00.617' AS DateTime), 1, N'thinhtpt.2000@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'YXS43YYy', N'Music For Today', N'Hôm nay trời mưa nên cũng khá mát. Làm gì để tận hưởng không khí này đây? Ăn miếng bánh trung thu, uống 1 tách trà nóng và thưởng thức 1 vài bài nhạc để "deep" cùng mưa nè. Nếu bạn là 1 fan của nhạc Hoa, đừng bỏ qua list nhạc này nhé ! ^ ^
<a href="https://www.youtube.com/watch?v=zXkNjhJU4mo" target="_blank">Nhớ em -Ngải Thần </a>
<a href="https://www.youtube.com/watch?v=6XouWKPvdag" target="_blank">Người tôi yêu - Lâm Thu Văn</a>
<a href="https://www.youtube.com/watch?v=pMxMXYr_DRg" target="_blank">Diễn viên - Tiết Chi Khiêm</a>
<a href="https://www.youtube.com/watch?v=JZnw8KYMM6o" target="_blank">Mashup Tuyết chân thật + Kẻ xấu xí + Người mẫu + Quý ông + Bố mẹ + Diễn viên</a>
', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601304266/social-network-images-upload/fo4nqmdtys3mtcullsxy.jpg', CAST(N'2020-09-28 21:44:33.677' AS DateTime), 1, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'Z1jXWPxG', N'Music For Today ?', N'Hôm nay trời mưa nên cũng khá mát. Làm gì để tận hưởng không khí này đây? Ăn miếng bánh trung thu, uống 1 tách trà nóng và thưởng thức 1 vài bài nhạc để "deep" cùng mưa nè. Nếu bạn là 1 fan của nhạc Hoa, đừng bỏ qua list nhạc này nhé ! ^ ^
Nhớ em -Ngải Thần https://www.youtube.com/watch?v=zXkNjhJU4mo
Người tôi yêu - Lâm Thu Văn https://www.youtube.com/watch?v=6XouWKPvdag
Diễn viên - Tiết Chi Khiêm https://www.youtube.com/watch?v=pMxMXYr_DRg
Mashup Tuyết chân thật + Kẻ xấu xí + Người mẫu + Quý ông + Bố mẹ + Diễn viên
https://www.youtube.com/watch?v=JZnw8KYMM6o', N'https://res.cloudinary.com/thinhtpt/image/upload/v1601303955/social-network-images-upload/vfldkbysj4fq4gq5jmuc.jpg', CAST(N'2020-09-28 21:39:27.893' AS DateTime), 0, N'beiuleagueoflegend@gmail.com')
INSERT [dbo].[tblArticles] ([id], [title], [description], [imageLink], [date], [status], [postBy]) VALUES (N'zAnGIPrl', N'Học ReactJS ở đâu "xịn xò" nhất?', N'Câu trả lời cho việc chọn nơi học ReactJS online "xịn xò" và "đầy đủ" nhất chính là...

<a href="https://reactjs.org/" target="_blank">TẠI ĐÂY</a>', N'', CAST(N'2020-09-30 20:22:05.877' AS DateTime), 1, N'thinhtpt.2000@gmail.com')
INSERT [dbo].[tblArticleStatus] ([statusId], [statusName]) VALUES (1, N'Active')
INSERT [dbo].[tblArticleStatus] ([statusId], [statusName]) VALUES (0, N'Delete')
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'0AR3BWEi', N'Nhạc hay, đúng tâm trạng tác giả rồi :))))', N'YXS43YYy', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:50:49.360' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'0tYniBib', N'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aa
aaaaa
a
â
a', N'oRx1udvD', N'tthinh2128@gmail.com', 2, CAST(N'2020-09-28 21:41:52.843' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'0XjAMgRf', N'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb', N'oRx1udvD', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:31:13.047' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'c2IxxX8I', N'một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài một câu dài ', N'oRx1udvD', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:42:10.977' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'dJYAZCVB', N'AAD', N'X783niDH', N'beiuonepiece999@gmail.com', 2, CAST(N'2020-09-30 20:22:46.363' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'DTLO2vwg', N'Xịn nha :3', N'TJgO13Rc', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:04:47.987' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'hEQiHEAT', N'Cool!', N'zAnGIPrl', N'beiuonepiece999@gmail.com', 1, CAST(N'2020-09-30 20:25:57.507' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'HkUYWVBe', N'khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng khiêng ', N'oRx1udvD', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:43:25.757' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'hUfoyB6N', N'Hay đấy!
Môt like :)', N'2yP0Njvq', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-28 11:28:18.140' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'iEgORJqH', N'Mưa to lắm!', N'2Wyj2FV2', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-29 10:15:06.670' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'ieivTKzA', N'Nhạc buồn tác giả cũng buồn :''<
', N'YXS43YYy', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-30 20:02:00.157' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'IRZQHQQr', N'ADS', N'X783niDH', N'beiuonepiece999@gmail.com', 2, CAST(N'2020-09-30 20:22:02.680' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'iyqhDbS0', N'Vẫn ướt nhưng không ướt bên ngoài, trái tim khóc nên ướt ...', N'2Wyj2FV2', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:12:32.417' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'jfkYmXQc', N'asdas', N'X783niDH', N'beiuonepiece999@gmail.com', 2, CAST(N'2020-09-30 20:22:04.353' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'KxEjqoIh', N'Cmt cũng nên để nhiều dòng nhỉ?
Đồng ý không?', N'bIEfRaV6', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 11:23:48.703' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'SJRTgzD1', N'AAAA', N'oRx1udvD', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-29 08:33:49.837' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'SOe4DqfA', N'AAAA', N'Sd5FdD14', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-29 08:33:09.263' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'sOeNY7JJ', N'Good job!', N'BIKvk8Dd', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 19:50:10.800' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'T72C0ztg', N'Nope!!!', N'KHFco2AM', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-30 20:00:37.997' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'WFtyCbFA', N'Đẹp!', N'HAW0j3Dl', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-28 11:44:46.220' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'xUWfmtry', N'Thơ hay :))))', N'Khx9iS3u', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:07:36.763' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'YirGl7iU', N'BBBB', N'Sd5FdD14', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-30 20:02:15.903' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'ZpMfeD5O', N'Ai đẹp trai thế', N'Khx9iS3u', N'beiuleagueoflegend@gmail.com', 2, CAST(N'2020-09-28 21:07:34.223' AS DateTime))
INSERT [dbo].[tblComments] ([commentId], [text], [articleId], [userId], [status], [time]) VALUES (N'ZrJtleWs', N'Cập nhật thêm rồi bạn ơi!!!', N'sqiXNlKu', N'thinhtpt.2000@gmail.com', 1, CAST(N'2020-09-30 19:58:22.910' AS DateTime))
INSERT [dbo].[tblCommentStatus] ([statusId], [name]) VALUES (1, N'Active')
INSERT [dbo].[tblCommentStatus] ([statusId], [name]) VALUES (2, N'Delete')
INSERT [dbo].[tblEventType] ([typeId], [name]) VALUES (1, N'Comment')
INSERT [dbo].[tblEventType] ([typeId], [name]) VALUES (3, N'Dislike')
INSERT [dbo].[tblEventType] ([typeId], [name]) VALUES (2, N'Like')
INSERT [dbo].[tblEventType] ([typeId], [name]) VALUES (0, N'None')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'3mnSmQIY', 2, CAST(N'2020-09-28 11:44:41.867' AS DateTime), NULL, N'xBED7Td9')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'5e3eiqBu', 2, CAST(N'2020-09-30 10:56:43.977' AS DateTime), NULL, N'ls9WldDW')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'BJlEh7ms', 2, CAST(N'2020-09-28 21:12:07.030' AS DateTime), NULL, N'oLRJyOLx')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'CfyikuUX', 2, CAST(N'2020-09-28 21:07:17.387' AS DateTime), NULL, N'OjAjJYBM')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'D1JTq1RT', 3, CAST(N'2020-09-30 20:00:33.580' AS DateTime), NULL, N'ttShW4Xk')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'DNp4HBS4', 3, CAST(N'2020-09-28 11:32:54.267' AS DateTime), NULL, N'fIfq2xIj')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'eSIEejH7', 1, CAST(N'2020-09-28 21:50:49.360' AS DateTime), N'0AR3BWEi', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'f8mbmmIr', 2, CAST(N'2020-09-28 21:04:41.450' AS DateTime), NULL, N'8xiTfzeY')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'ffEK6ypS', 1, CAST(N'2020-09-30 20:00:37.997' AS DateTime), N'T72C0ztg', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'fhg6h8FQ', 1, CAST(N'2020-09-30 20:25:57.507' AS DateTime), N'hEQiHEAT', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'h2OKAOHN', 1, CAST(N'2020-09-28 11:28:18.140' AS DateTime), N'hUfoyB6N', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'iR0L4xsf', 2, CAST(N'2020-09-28 21:53:58.177' AS DateTime), NULL, N'RvpIbIF8')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'l7WpcX5W', 2, CAST(N'2020-09-30 16:07:41.953' AS DateTime), NULL, N'57KYPj2Q')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'LAmqbGOO', 1, CAST(N'2020-09-28 11:44:46.220' AS DateTime), N'WFtyCbFA', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'mZLkNJ9D', 1, CAST(N'2020-09-30 19:50:10.800' AS DateTime), N'sOeNY7JJ', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'P2vxVaeW', 1, CAST(N'2020-09-30 19:58:22.910' AS DateTime), N'ZrJtleWs', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'P8Ekwbug', 1, CAST(N'2020-09-28 21:07:36.763' AS DateTime), N'xUWfmtry', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'qGYmnv24', 2, CAST(N'2020-09-30 19:57:57.550' AS DateTime), NULL, N'l8Q1JAOx')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'qStOyOaU', 1, CAST(N'2020-09-28 11:23:48.703' AS DateTime), N'KxEjqoIh', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'SunOTbnD', 1, CAST(N'2020-09-29 08:33:09.263' AS DateTime), N'SOe4DqfA', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'tADX0y4j', 2, CAST(N'2020-09-29 14:03:18.463' AS DateTime), NULL, N'RaEq4Z11')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'twmbdylI', 1, CAST(N'2020-09-28 21:04:47.987' AS DateTime), N'DTLO2vwg', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'ugHB9Rem', 1, CAST(N'2020-09-28 21:31:13.047' AS DateTime), N'0XjAMgRf', NULL)
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'uQtMiqqe', 2, CAST(N'2020-09-30 20:25:48.757' AS DateTime), NULL, N'KapkfTai')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'uYL90glk', 2, CAST(N'2020-09-28 11:28:19.707' AS DateTime), NULL, N'TzIXJUr6')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'V38fnzJG', 3, CAST(N'2020-09-30 19:52:42.283' AS DateTime), NULL, N'9KS5Wl0K')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'vg6dFMZe', 2, CAST(N'2020-09-30 19:45:10.300' AS DateTime), NULL, N'ZW9z96Ge')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'voWTEaQW', 2, CAST(N'2020-09-30 20:22:31.093' AS DateTime), NULL, N'88TzMUku')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'waCRa2yo', 2, CAST(N'2020-09-28 21:46:10.693' AS DateTime), NULL, N'uCMAZGeH')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'wj8K8Kft', 2, CAST(N'2020-09-30 09:38:58.000' AS DateTime), NULL, N'hQ0gISI9')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'XLXjPAQk', 2, CAST(N'2020-09-30 19:50:04.117' AS DateTime), NULL, N'uhV35Ng4')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'xUHNQTbg', 3, CAST(N'2020-09-30 10:57:53.657' AS DateTime), NULL, N'Fa3fWc6g')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'y4uEpdSP', 3, CAST(N'2020-09-30 10:58:36.547' AS DateTime), NULL, N'IzTeYrpI')
INSERT [dbo].[tblNotifications] ([notificationId], [eventType], [time], [commentId], [reactionId]) VALUES (N'zay2Z9ie', 1, CAST(N'2020-09-28 21:12:32.417' AS DateTime), N'iyqhDbS0', NULL)
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'57KYPj2Q', N'GZWRvgFC', N'thinhtpt.temp@gmail.com', 1, CAST(N'2020-09-30 16:07:41.953' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'88TzMUku', N'X783niDH', N'thinhtpt.2000@gmail.com', 1, CAST(N'2020-09-30 20:22:31.093' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'8xiTfzeY', N'TJgO13Rc', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:04:41.450' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'9KS5Wl0K', N'ibTvTFgZ', N'tthinh2128@gmail.com', 2, CAST(N'2020-09-30 19:52:42.283' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'Fa3fWc6g', N'Sd5FdD14', N'nghiadhse140362@fpt.edu.vn', 2, CAST(N'2020-09-30 10:57:53.657' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'fIfq2xIj', N'TJgO13Rc', N'tthinh2128@gmail.com', 2, CAST(N'2020-09-28 11:32:54.267' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'GGW5CPxV', N'sQTFmi0O', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-29 10:20:14.827' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'hQ0gISI9', N'Jk1o5NPe', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-30 09:38:58.000' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'IzTeYrpI', N'YXS43YYy', N'nghiadhse140362@fpt.edu.vn', 2, CAST(N'2020-09-30 10:58:36.547' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'KapkfTai', N'zAnGIPrl', N'beiuonepiece999@gmail.com', 1, CAST(N'2020-09-30 20:25:48.757' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'kkTpeGIh', N'KRnhfVvE', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-29 08:36:33.773' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'l8Q1JAOx', N'sqiXNlKu', N'thinhtpt.2000@gmail.com', 1, CAST(N'2020-09-30 19:57:57.550' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'ls9WldDW', N'I5DF6Gnx', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 10:56:43.977' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'M45z6wUX', N'Khx9iS3u', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:07:45.703' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'OjAjJYBM', N'Khx9iS3u', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:07:17.387' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'oLRJyOLx', N'2Wyj2FV2', N'beiuleagueoflegend@gmail.com', 1, CAST(N'2020-09-28 21:12:07.030' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'RaEq4Z11', N'Sd5FdD14', N'huy.trnminh@gmail.com', 1, CAST(N'2020-09-29 14:03:18.463' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'RvpIbIF8', N'Sd5FdD14', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:53:58.177' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'sRoQFMkj', N'OzVz7Of0', N'thinhtpt.2000@gmail.com', 1, CAST(N'2020-09-30 20:36:31.217' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'tNZxckxJ', N'GZWRvgFC', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 16:04:23.157' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'ttShW4Xk', N'KHFco2AM', N'thinhtptse140092@fpt.edu.vn', 2, CAST(N'2020-09-30 20:00:33.580' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'TzIXJUr6', N'2yP0Njvq', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-28 11:28:19.707' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'uCMAZGeH', N'YXS43YYy', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-28 21:46:10.693' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'uhV35Ng4', N'BIKvk8Dd', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 19:50:04.117' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'UVQ3eCKQ', N'X783niDH', N'beiuonepiece999@gmail.com', 0, CAST(N'2020-09-30 20:25:16.290' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'V0BxtCWz', N'2Wyj2FV2', N'tthinh2128@gmail.com', 2, CAST(N'2020-09-29 10:15:01.347' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'wDEliPGg', N'Jk1o5NPe', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 16:02:23.610' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'xAgSFkoV', N'e2ptLLDH', N'nghiadhse140362@fpt.edu.vn', 1, CAST(N'2020-09-30 10:56:34.527' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'xBED7Td9', N'HAW0j3Dl', N'thinhtptse140092@fpt.edu.vn', 1, CAST(N'2020-09-28 11:44:41.867' AS DateTime))
INSERT [dbo].[tblReactions] ([reactionId], [articleId], [userId], [typeId], [time]) VALUES (N'ZW9z96Ge', N'7rgcgy5t', N'tthinh2128@gmail.com', 1, CAST(N'2020-09-30 19:45:10.300' AS DateTime))
INSERT [dbo].[tblReactionTypes] ([typeId], [name]) VALUES (2, N'Dislike')
INSERT [dbo].[tblReactionTypes] ([typeId], [name]) VALUES (1, N'Like')
INSERT [dbo].[tblReactionTypes] ([typeId], [name]) VALUES (0, N'None')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (2, N'Active')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (1, N'New')
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'beiuleagueoflegend@gmail.com', N'Lê Đạt Anh Khôi', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'beiuonepiece999@gmail.com', N'Hứa Vĩnh Khang', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'huy.trnminh@gmail.com', N'Huy', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'khoildase140074@fpt.edu.vn', N'Lê Đạt Anh Khôi', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 1)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'nghiadhse140362@fpt.edu.vn', N'涅炎', N'e423d8071db43917b0863f4967487c6422ed7b588e2a7135ee9fcf1348e82711', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'thinhtpt.2000@gmail.com', N'Another Thịnh', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'thinhtpt.temp@gmail.com', N'ThinhTPT', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'thinhtptse140092@fpt.edu.vn', N'Thịnh FPT', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'tthinh.2128@gmail.com', N'Thinh', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1)
INSERT [dbo].[tblUsers] ([email], [name], [password], [statusId]) VALUES (N'tthinh2128@gmail.com', N'Thịnh Trần', N'2c09fe67aebd2f85d6394dfeff89d849b1df6fb7e84bf0c3c58f0e03a1dcdc4d', 2)
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'2NjEvigb', N'thinhtptse140092@fpt.edu.vn', N'PFWFU4', CAST(N'2020-09-28 11:17:34.760' AS DateTime), CAST(N'2020-09-28 13:17:34.760' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'2StQqAu7', N'thinhtpt.temp@gmail.com', N'ZAW1FR', CAST(N'2020-09-30 16:06:59.930' AS DateTime), CAST(N'2020-09-30 18:06:59.930' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'3Rv7KZ5X', N'thinhtpt.2000@gmail.com', N'1Q5A91', CAST(N'2020-09-30 11:46:54.040' AS DateTime), CAST(N'2020-09-30 13:46:54.040' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'6d7P4hOk', N'huy.trnminh@gmail.com', N'UUB4TM', CAST(N'2020-09-29 13:57:53.537' AS DateTime), CAST(N'2020-09-29 15:57:53.537' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'7LK8mBml', N'tthinh.2128@gmail.com', N'13CBMV', CAST(N'2020-09-30 11:39:39.887' AS DateTime), CAST(N'2020-09-30 13:39:39.887' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'8Icnv0af', N'thinhtpt.2000@gmail.com', N'UX5LKT', CAST(N'2020-09-30 14:20:55.127' AS DateTime), CAST(N'2020-09-30 16:20:55.127' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'DcmalLS1', N'thinhtpt.2000@gmail.com', N'XUPJMA', CAST(N'2020-09-30 14:21:00.590' AS DateTime), CAST(N'2020-09-30 16:21:00.590' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'dYT7XFdo', N'thinhtpt.2000@gmail.com', N'4TFIIQ', CAST(N'2020-09-30 14:20:47.840' AS DateTime), CAST(N'2020-09-30 16:20:47.840' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'FsmZPjIM', N'tthinh2128@gmail.com', N'F01G1B', CAST(N'2020-09-28 11:12:52.117' AS DateTime), CAST(N'2020-09-28 13:12:52.117' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'ge3Ebqq1', N'beiuonepiece999@gmail.com', N'9E7RMK', CAST(N'2020-09-30 20:06:10.963' AS DateTime), CAST(N'2020-09-30 22:06:10.963' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'hrijYyQY', N'nghiadhse140362@fpt.edu.vn', N'ZDOY9O', CAST(N'2020-09-30 10:47:56.027' AS DateTime), CAST(N'2020-09-30 12:47:56.027' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'L8r2LbXa', N'beiuleagueoflegend@gmail.com', N'WDNBU2', CAST(N'2020-09-28 21:03:20.737' AS DateTime), CAST(N'2020-09-28 23:03:20.737' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'm3mWjOC7', N'thinhtpt.temp@gmail.com', N'GQJAWH', CAST(N'2020-09-30 16:05:51.647' AS DateTime), CAST(N'2020-09-30 18:05:51.647' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'mnw6unrJ', N'khoildase140074@fpt.edu.vn', N'FLA7XR', CAST(N'2020-09-28 21:29:31.573' AS DateTime), CAST(N'2020-09-28 23:29:31.573' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'ptqegRrd', N'thinhtpt.2000@gmail.com', N'GJUUUB', CAST(N'2020-09-30 11:46:39.757' AS DateTime), CAST(N'2020-09-30 13:46:39.757' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'VeAow19s', N'thinhtpt.2000@gmail.com', N'TSMNAA', CAST(N'2020-09-30 11:46:52.020' AS DateTime), CAST(N'2020-09-30 13:46:52.020' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'WLE045MF', N'thinhtpt.temp@gmail.com', N'IGRBS1', CAST(N'2020-09-30 16:06:57.310' AS DateTime), CAST(N'2020-09-30 18:06:57.310' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'wqeFlzr1', N'khoildase140074@fpt.edu.vn', N'XRMM4W', CAST(N'2020-09-28 21:29:45.237' AS DateTime), CAST(N'2020-09-28 23:29:45.237' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'wrX4hH2O', N'khoildase140074@fpt.edu.vn', N'P7RKGD', CAST(N'2020-09-28 21:29:53.320' AS DateTime), CAST(N'2020-09-28 23:29:53.320' AS DateTime))
INSERT [dbo].[tblVerifyUser] ([id], [email], [code], [requestTime], [expireTime]) VALUES (N'Xq4UF4wc', N'thinhtpt.2000@gmail.com', N'LJ9BQZ', CAST(N'2020-09-30 19:56:57.573' AS DateTime), CAST(N'2020-09-30 21:56:57.573' AS DateTime))
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_tblArticleStatus]    Script Date: 30/09/2020 20:42:05 ******/
ALTER TABLE [dbo].[tblArticleStatus] ADD  CONSTRAINT [IX_tblArticleStatus] UNIQUE NONCLUSTERED 
(
	[statusName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_tblCommentStatus]    Script Date: 30/09/2020 20:42:05 ******/
ALTER TABLE [dbo].[tblCommentStatus] ADD  CONSTRAINT [IX_tblCommentStatus] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_tblEventType]    Script Date: 30/09/2020 20:42:05 ******/
ALTER TABLE [dbo].[tblEventType] ADD  CONSTRAINT [IX_tblEventType] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_tblReactionTypes]    Script Date: 30/09/2020 20:42:05 ******/
ALTER TABLE [dbo].[tblReactionTypes] ADD  CONSTRAINT [IX_tblReactionTypes] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_tblStatus]    Script Date: 30/09/2020 20:42:05 ******/
ALTER TABLE [dbo].[tblStatus] ADD  CONSTRAINT [IX_tblStatus] UNIQUE NONCLUSTERED 
(
	[statusName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblArticles]  WITH CHECK ADD  CONSTRAINT [FK_Posts_Users] FOREIGN KEY([postBy])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblArticles] CHECK CONSTRAINT [FK_Posts_Users]
GO
ALTER TABLE [dbo].[tblArticles]  WITH CHECK ADD  CONSTRAINT [FK_tblArticles_tblArticleStatus] FOREIGN KEY([status])
REFERENCES [dbo].[tblArticleStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblArticles] CHECK CONSTRAINT [FK_tblArticles_tblArticleStatus]
GO
ALTER TABLE [dbo].[tblComments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Posts] FOREIGN KEY([articleId])
REFERENCES [dbo].[tblArticles] ([id])
GO
ALTER TABLE [dbo].[tblComments] CHECK CONSTRAINT [FK_Comments_Posts]
GO
ALTER TABLE [dbo].[tblComments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Users] FOREIGN KEY([userId])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblComments] CHECK CONSTRAINT [FK_Comments_Users]
GO
ALTER TABLE [dbo].[tblComments]  WITH CHECK ADD  CONSTRAINT [FK_tblComments_tblCommentStatus] FOREIGN KEY([status])
REFERENCES [dbo].[tblCommentStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblComments] CHECK CONSTRAINT [FK_tblComments_tblCommentStatus]
GO
ALTER TABLE [dbo].[tblNotifications]  WITH CHECK ADD  CONSTRAINT [FK_tblNotifications_tblComments] FOREIGN KEY([commentId])
REFERENCES [dbo].[tblComments] ([commentId])
GO
ALTER TABLE [dbo].[tblNotifications] CHECK CONSTRAINT [FK_tblNotifications_tblComments]
GO
ALTER TABLE [dbo].[tblNotifications]  WITH CHECK ADD  CONSTRAINT [FK_tblNotifications_tblEventType] FOREIGN KEY([eventType])
REFERENCES [dbo].[tblEventType] ([typeId])
GO
ALTER TABLE [dbo].[tblNotifications] CHECK CONSTRAINT [FK_tblNotifications_tblEventType]
GO
ALTER TABLE [dbo].[tblNotifications]  WITH CHECK ADD  CONSTRAINT [FK_tblNotifications_tblReactions] FOREIGN KEY([reactionId])
REFERENCES [dbo].[tblReactions] ([reactionId])
GO
ALTER TABLE [dbo].[tblNotifications] CHECK CONSTRAINT [FK_tblNotifications_tblReactions]
GO
ALTER TABLE [dbo].[tblReactions]  WITH CHECK ADD  CONSTRAINT [FK_Reactions_Posts] FOREIGN KEY([articleId])
REFERENCES [dbo].[tblArticles] ([id])
GO
ALTER TABLE [dbo].[tblReactions] CHECK CONSTRAINT [FK_Reactions_Posts]
GO
ALTER TABLE [dbo].[tblReactions]  WITH CHECK ADD  CONSTRAINT [FK_Reactions_Users] FOREIGN KEY([userId])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblReactions] CHECK CONSTRAINT [FK_Reactions_Users]
GO
ALTER TABLE [dbo].[tblReactions]  WITH CHECK ADD  CONSTRAINT [FK_tblReactions_tblReactionTypes] FOREIGN KEY([typeId])
REFERENCES [dbo].[tblReactionTypes] ([typeId])
GO
ALTER TABLE [dbo].[tblReactions] CHECK CONSTRAINT [FK_tblReactions_tblReactionTypes]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblStatus]
GO
ALTER TABLE [dbo].[tblVerifyUser]  WITH CHECK ADD  CONSTRAINT [FK_tblVerifyUser_tblUsers] FOREIGN KEY([email])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblVerifyUser] CHECK CONSTRAINT [FK_tblVerifyUser_tblUsers]
GO
USE [master]
GO
ALTER DATABASE [SOCIAL_NETWORK_DB] SET  READ_WRITE 
GO
