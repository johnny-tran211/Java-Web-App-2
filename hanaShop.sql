USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 3/1/2020 11:09:32 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HanaShop.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[CheckOutDetails]    Script Date: 3/1/2020 11:09:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CheckOutDetails](
	[CheckOutID] [int] NOT NULL,
	[ProductName] [varchar](100) NOT NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL,
	[Total] [float] NULL,
 CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED 
(
	[CheckOutID] ASC,
	[ProductName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CheckOutHistory]    Script Date: 3/1/2020 11:09:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CheckOutHistory](
	[CheckOutID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](50) NULL,
	[CheckOutDate] [smalldatetime] NULL,
	[PaymentMethod] [varchar](50) NULL,
	[TotalPrice] [float] NULL,
 CONSTRAINT [PK_CheckOutHistory] PRIMARY KEY CLUSTERED 
(
	[CheckOutID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Items]    Script Date: 3/1/2020 11:09:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Items](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[itemName] [varchar](100) NOT NULL,
	[status] [varchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[createDate] [smalldatetime] NOT NULL,
	[image] [varchar](100) NULL,
	[description] [text] NULL,
	[price] [float] NOT NULL,
	[category] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Items_1] PRIMARY KEY CLUSTERED 
(
	[itemName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Login]    Script Date: 3/1/2020 11:09:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Login](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullname] [varchar](50) NOT NULL,
	[role] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Login] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UpdateHistory]    Script Date: 3/1/2020 11:09:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UpdateHistory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](50) NULL,
	[ItemName] [varchar](100) NULL,
	[UpdateDate] [smalldatetime] NULL,
	[Status] [varchar](50) NULL,
 CONSTRAINT [PK_UpdateHistory] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (12, N'fried rice', 2, 12.5, 25)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (12, N'noodle', 2, 10, 20)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (12, N'pepsi', 1, 10, 10)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (13, N'sprite', 7, 52, 364)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (14, N'banana', 3, 17.5, 52.5)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (14, N'coca', 2, 10, 20)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (15, N'red sting', 2, 90, 180)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (16, N'red sting', 2, 90, 180)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (16, N'sprite', 1, 52, 52)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (17, N'banana', 1, 17.5, 17.5)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (17, N'coca', 1, 10, 10)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (18, N'banana', 1, 17.5, 17.5)
INSERT [dbo].[CheckOutDetails] ([CheckOutID], [ProductName], [Quantity], [Price], [Total]) VALUES (19, N'banana', 2, 17.5, 35)
SET IDENTITY_INSERT [dbo].[CheckOutHistory] ON 

INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (12, N'dhoang2101@gmail.com', CAST(N'2020-03-01 16:27:00' AS SmallDateTime), N'Cash', 55)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (13, N'dhoang2101@gmail.com', CAST(N'2020-03-02 17:00:00' AS SmallDateTime), N'Cash', 364)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (14, N'dhoang2101@gmail.com', CAST(N'2020-03-01 18:22:00' AS SmallDateTime), N'Cash', 72.5)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (15, N'dhoang2101@gmail.com', CAST(N'2020-03-01 18:24:00' AS SmallDateTime), N'Cash', 180)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (16, N'dhoang2101@gmail.com', CAST(N'2020-03-01 22:37:00' AS SmallDateTime), N'Cash', 232)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (17, N'dhoang2101@gmail.com', CAST(N'2020-03-01 22:40:00' AS SmallDateTime), N'Cash', 27.5)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (18, N'dhoang2101@gmail.com', CAST(N'2020-03-01 22:48:00' AS SmallDateTime), N'Cash', 17.5)
INSERT [dbo].[CheckOutHistory] ([CheckOutID], [Email], [CheckOutDate], [PaymentMethod], [TotalPrice]) VALUES (19, N'dhoang2101@gmail.com', CAST(N'2020-03-01 22:50:00' AS SmallDateTime), N'Cash', 35)
SET IDENTITY_INSERT [dbo].[CheckOutHistory] OFF
SET IDENTITY_INSERT [dbo].[Items] ON 

INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (1, N'banana', N'active', 8, CAST(N'2020-02-25 23:26:00' AS SmallDateTime), N'banana-juice.jpg', N'trai cay', 17.5, N'fruit')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (2, N'coca', N'active', 0, CAST(N'2020-02-12 17:54:00' AS SmallDateTime), N'coca.jpg', N'coca cola', 10, N'drink')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (10, N'fried rice', N'Inactive', 3, CAST(N'2020-02-27 04:58:00' AS SmallDateTime), N'fried-rice.jpg', N'high rice', 12.5, N'aaaa')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (3, N'heniken', N'active', 1, CAST(N'2020-02-12 21:01:00' AS SmallDateTime), N'heineken.jpg', N'kind of beer', 10, N'drink')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (11, N'ice-cream', N'active', 10, CAST(N'2020-02-29 22:39:00' AS SmallDateTime), N'ice-cream.jpg', N'ice, sweet', 51, N'dessert')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (4, N'noodle', N'active', 3, CAST(N'2020-02-12 22:39:00' AS SmallDateTime), N'noodle.jpg', N'fast food', 10, N'fast food')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (5, N'pepsi', N'active', 3, CAST(N'2020-02-12 17:33:00' AS SmallDateTime), N'pepsi.png', N'hello', 10, N'drink')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (7, N'red sting', N'active', 7, CAST(N'2020-02-12 22:51:00' AS SmallDateTime), N'sting.jpg', N'kind of soft water', 90, N'drink')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (15, N'Spaghetti', N'active', 5, CAST(N'2020-03-01 22:55:00' AS SmallDateTime), N'spaghetti.jpg', N'yummy', 150, N'food')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (6, N'sprite', N'active', 3, CAST(N'2020-02-12 23:37:00' AS SmallDateTime), N'sprite.jpg', N'aaaa', 52, N'drink')
INSERT [dbo].[Items] ([id], [itemName], [status], [quantity], [createDate], [image], [description], [price], [category]) VALUES (18, N'Tea', N'active', 5, CAST(N'2020-03-01 22:59:00' AS SmallDateTime), N'tea.jpg', N'tea', 15, N'drink')
SET IDENTITY_INSERT [dbo].[Items] OFF
INSERT [dbo].[Login] ([username], [password], [fullname], [role]) VALUES (N'dhoang2101@gmail.com', N'duyhoang2511', N'hoang tran', N'User')
INSERT [dbo].[Login] ([username], [password], [fullname], [role]) VALUES (N'hoangtd@gmail.com', N'duyhoang2511', N'duy hoang', N'Admin')
SET IDENTITY_INSERT [dbo].[UpdateHistory] ON 

INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (19, N'hoangtd@gmail.com', N'fried rice', CAST(N'2020-03-01 04:52:00' AS SmallDateTime), N'Delete')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (20, N'hoangtd@gmail.com', N'ice-cream', CAST(N'2020-03-01 04:52:00' AS SmallDateTime), N'Delete')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (21, N'hoangtd@gmail.com', N'banana', CAST(N'2020-03-01 04:53:00' AS SmallDateTime), N'Delete')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (22, N'hoangtd@gmail.com', N'red sting', CAST(N'2020-03-01 04:53:00' AS SmallDateTime), N'Delete')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (23, N'hoangtd@gmail.com', N'red sting', CAST(N'2020-03-01 04:53:00' AS SmallDateTime), N'Delete')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (24, N'hoangtd@gmail.com', N'ice-cream', CAST(N'2020-03-01 05:28:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (25, N'hoangtd@gmail.com', N'fried rice', CAST(N'2020-03-01 05:30:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (26, N'hoangtd@gmail.com', N'sprite', CAST(N'2020-03-01 16:24:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (27, N'hoangtd@gmail.com', N'fried rice', CAST(N'2020-03-01 16:24:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (28, N'hoangtd@gmail.com', N'banana', CAST(N'2020-03-01 16:24:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (29, N'hoangtd@gmail.com', N'red sting', CAST(N'2020-03-01 16:24:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (30, N'hoangtd@gmail.com', N'red sting', CAST(N'2020-03-01 16:25:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (31, N'hoangtd@gmail.com', N'fried rice', CAST(N'2020-03-01 22:28:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (32, N'hoangtd@gmail.com', N'pepsi', CAST(N'2020-03-01 22:28:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (33, N'hoangtd@gmail.com', N'noodle', CAST(N'2020-03-01 22:28:00' AS SmallDateTime), N'Update')
INSERT [dbo].[UpdateHistory] ([Id], [Email], [ItemName], [UpdateDate], [Status]) VALUES (34, N'hoangtd@gmail.com', N'fried rice', CAST(N'2020-03-01 22:34:00' AS SmallDateTime), N'Delete')
SET IDENTITY_INSERT [dbo].[UpdateHistory] OFF
ALTER TABLE [dbo].[CheckOutDetails]  WITH CHECK ADD  CONSTRAINT [FK_CheckOutDetails_CheckOutHistory] FOREIGN KEY([CheckOutID])
REFERENCES [dbo].[CheckOutHistory] ([CheckOutID])
GO
ALTER TABLE [dbo].[CheckOutDetails] CHECK CONSTRAINT [FK_CheckOutDetails_CheckOutHistory]
GO
ALTER TABLE [dbo].[CheckOutDetails]  WITH CHECK ADD  CONSTRAINT [FK_CheckOutDetails_Items] FOREIGN KEY([ProductName])
REFERENCES [dbo].[Items] ([itemName])
GO
ALTER TABLE [dbo].[CheckOutDetails] CHECK CONSTRAINT [FK_CheckOutDetails_Items]
GO
ALTER TABLE [dbo].[CheckOutHistory]  WITH CHECK ADD  CONSTRAINT [FK_CheckOutHistory_Login] FOREIGN KEY([Email])
REFERENCES [dbo].[Login] ([username])
GO
ALTER TABLE [dbo].[CheckOutHistory] CHECK CONSTRAINT [FK_CheckOutHistory_Login]
GO
ALTER TABLE [dbo].[UpdateHistory]  WITH CHECK ADD  CONSTRAINT [FK_UpdateHistory_Login] FOREIGN KEY([Email])
REFERENCES [dbo].[Login] ([username])
GO
ALTER TABLE [dbo].[UpdateHistory] CHECK CONSTRAINT [FK_UpdateHistory_Login]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
