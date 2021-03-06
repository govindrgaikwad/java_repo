USE [master]
GO
/****** Object:  Database [Service]    Script Date: 07/11/2014 17:36:40 ******/
CREATE DATABASE [Service] ON  PRIMARY 
( NAME = N'ServiceGenerator', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\ServiceGenerator.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ServiceGenerator_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\ServiceGenerator_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Service] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Service].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Service] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [Service] SET ANSI_NULLS OFF
GO
ALTER DATABASE [Service] SET ANSI_PADDING OFF
GO
ALTER DATABASE [Service] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [Service] SET ARITHABORT OFF
GO
ALTER DATABASE [Service] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [Service] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [Service] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [Service] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [Service] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [Service] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [Service] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [Service] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [Service] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [Service] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [Service] SET  DISABLE_BROKER
GO
ALTER DATABASE [Service] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [Service] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [Service] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [Service] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [Service] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [Service] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [Service] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [Service] SET  READ_WRITE
GO
ALTER DATABASE [Service] SET RECOVERY SIMPLE
GO
ALTER DATABASE [Service] SET  MULTI_USER
GO
ALTER DATABASE [Service] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [Service] SET DB_CHAINING OFF
GO
USE [Service]
GO
/****** Object:  Schema [Data]    Script Date: 07/11/2014 17:36:40 ******/
CREATE SCHEMA [Data] AUTHORIZATION [db_accessadmin]
GO
/****** Object:  Table [Data].[Project]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[Project](
	[ProjectId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](500) NULL,
	[CompanyName] [nvarchar](200) NULL,
	[Client] [nvarchar](50) NULL,
	[Location] [nvarchar](50) NULL,
	[Manager] [nvarchar](50) NULL,
	[Leader] [nvarchar](50) NULL,
	[Status] [nvarchar](50) NULL,
	[StartDate] [date] NOT NULL,
	[ReleaseDate] [date] NULL,
 CONSTRAINT [PK_Project] PRIMARY KEY CLUSTERED 
(
	[ProjectId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[DataType]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[DataType](
	[DataTypeId] [int] IDENTITY(1,1) NOT NULL,
	[ConstantValue] [int] NOT NULL,
	[SQLType] [nvarchar](50) NOT NULL,
	[JavaType] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_DataType] PRIMARY KEY CLUSTERED 
(
	[DataTypeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [Uniq_DataType] UNIQUE NONCLUSTERED 
(
	[SQLType] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[Role]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[Role](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](200) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[User]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[User](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[RoleId] [int] NOT NULL,
	[UserName] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](200) NULL,
	[Password] [nvarchar](50) NOT NULL,
	[PhoneNumber] [numeric](10, 0) NULL,
	[Active] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[ProjectVersion](
	[ProjectVersionId] [int] IDENTITY(1,1) NOT NULL,
	[ProjectId] [int] NOT NULL,
	[VersionNumber] [int] NOT NULL,
	[Description] [nvarchar](500) NULL,
	[Status] [nvarchar](50) NULL,
	[StartDate] [date] NOT NULL,
	[ReleaseDate] [date] NOT NULL,
 CONSTRAINT [PK_ProjectVersion] PRIMARY KEY CLUSTERED 
(
	[ProjectVersionId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[ObjectDefination]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[ObjectDefination](
	[ObjectId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[SchemaName] [nvarchar](50) NULL,
	[DataBaseName] [nvarchar](50) NULL,
	[UserDefinedName] [nvarchar](50) NULL,
	[CamelCaseName] [nvarchar](50) NULL,
	[Embaddable] [bit] NOT NULL,
	[Updated] [bit] NOT NULL,
	[primaryKey] [bit] NOT NULL,
	[ProjectId] [int] NULL,
	[ProjectVersionId] [int] NULL,
	[CreatedBy] [nvarchar](100) NULL,
	[CreatedDate] [date] NULL,
	[UpdatedBy] [nvarchar](100) NULL,
	[UpdatedDate] [date] NULL,
 CONSTRAINT [PK_Entity] PRIMARY KEY CLUSTERED 
(
	[ObjectId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[Attribute]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[Attribute](
	[AttributeId] [int] IDENTITY(1,1) NOT NULL,
	[ObjectId] [int] NOT NULL,
	[ReferenceObjectId] [int] NULL,
	[Name] [nvarchar](50) NOT NULL,
	[UserDefinedName] [nvarchar](50) NULL,
	[CamelCaseName] [nvarchar](50) NULL,
	[ReferenceName] [nvarchar](100) NULL,
	[Datatype] [nvarchar](50) NOT NULL,
	[JavaDataType] [nvarchar](50) NOT NULL,
	[PrimaryKey] [bit] NOT NULL,
	[ForeignKey] [bit] NOT NULL,
	[Embaddable] [bit] NOT NULL,
	[ProjectId] [int] NOT NULL,
	[ProjectVersionId] [int] NOT NULL,
	[CreatedBy] [nvarchar](100) NOT NULL,
	[CreatedDate] [date] NOT NULL,
	[UpdatedBy] [nvarchar](100) NULL,
	[UpdatedDate] [date] NULL,
 CONSTRAINT [PK_Attribute] PRIMARY KEY CLUSTERED 
(
	[AttributeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[Method]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[Method](
	[MethodId] [int] IDENTITY(1,1) NOT NULL,
	[ObjectId] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[CamelCaseName] [nvarchar](50) NOT NULL,
	[AccessSpecifier] [nvarchar](50) NOT NULL,
	[ReturnType] [nvarchar](50) NOT NULL,
	[MethodType] [nvarchar](50) NOT NULL,
	[SoapOperationName] [nvarchar](50) NOT NULL,
	[SoapAction] [nvarchar](50) NOT NULL,
	[Exceptions] [nvarchar](500) NOT NULL,
	[ProjectId] [int] NOT NULL,
	[ProjectVersionId] [int] NOT NULL,
	[CreatedBy] [nvarchar](100) NOT NULL,
	[CreatedDate] [date] NOT NULL,
	[UpdatedBy] [nvarchar](100) NULL,
	[UpdatedDate] [date] NULL,
 CONSTRAINT [PK_Method] PRIMARY KEY CLUSTERED 
(
	[MethodId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[Embaddable]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[Embaddable](
	[EmbaddableId] [int] IDENTITY(1,1) NOT NULL,
	[ObjectId] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[CamelCaseName] [nvarchar](50) NULL,
	[ProjectId] [int] NOT NULL,
	[ProjectVersionId] [int] NOT NULL,
	[CreatedBy] [nvarchar](100) NOT NULL,
	[CreatedDate] [date] NOT NULL,
	[UpdatedBy] [nvarchar](100) NULL,
	[UpdatedDate] [date] NULL,
 CONSTRAINT [PK_Embaddable] PRIMARY KEY CLUSTERED 
(
	[EmbaddableId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Data].[Relationship]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [Data].[Relationship](
	[RelationshipId] [int] IDENTITY(1,1) NOT NULL,
	[ParentObjectId] [int] NOT NULL,
	[ChildObjectId] [int] NOT NULL,
	[ParentAttributeId] [int] NOT NULL,
	[ChildAttributeId] [int] NOT NULL,
	[RelationName] [nvarchar](50) NOT NULL,
	[CamelcaseName] [nvarchar](50) NOT NULL,
	[Cardinality] [char](1) NOT NULL,
	[ProjectId] [int] NOT NULL,
	[ProjectVersionId] [int] NOT NULL,
	[CreatedBy] [nvarchar](100) NOT NULL,
	[CreatedDate] [date] NOT NULL,
	[UpdatedBy] [nvarchar](100) NULL,
	[UpdatedDate] [date] NULL,
 CONSTRAINT [PK_Relationship] PRIMARY KEY CLUSTERED 
(
	[RelationshipId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [Data].[MethodParameter]    Script Date: 07/11/2014 17:36:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Data].[MethodParameter](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MethodId] [int] NOT NULL,
	[AttributeId] [int] NOT NULL,
 CONSTRAINT [PK_MethodParameter] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_User_Role]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([RoleId])
REFERENCES [Data].[Role] ([RoleId])
GO
ALTER TABLE [Data].[User] CHECK CONSTRAINT [FK_User_Role]
GO
/****** Object:  ForeignKey [FK_ProjectVersion_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[ProjectVersion]  WITH CHECK ADD  CONSTRAINT [FK_ProjectVersion_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[ProjectVersion] CHECK CONSTRAINT [FK_ProjectVersion_Project]
GO
/****** Object:  ForeignKey [FK_ObjectDefination_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[ObjectDefination]  WITH CHECK ADD  CONSTRAINT [FK_ObjectDefination_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[ObjectDefination] CHECK CONSTRAINT [FK_ObjectDefination_Project]
GO
/****** Object:  ForeignKey [FK_ObjectDefination_ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[ObjectDefination]  WITH CHECK ADD  CONSTRAINT [FK_ObjectDefination_ProjectVersion] FOREIGN KEY([ProjectVersionId])
REFERENCES [Data].[ProjectVersion] ([ProjectVersionId])
GO
ALTER TABLE [Data].[ObjectDefination] CHECK CONSTRAINT [FK_ObjectDefination_ProjectVersion]
GO
/****** Object:  ForeignKey [FK_Attribute_Entity]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Attribute]  WITH CHECK ADD  CONSTRAINT [FK_Attribute_Entity] FOREIGN KEY([ObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Attribute] CHECK CONSTRAINT [FK_Attribute_Entity]
GO
/****** Object:  ForeignKey [FK_Attribute_EntityReference]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Attribute]  WITH CHECK ADD  CONSTRAINT [FK_Attribute_EntityReference] FOREIGN KEY([ReferenceObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Attribute] CHECK CONSTRAINT [FK_Attribute_EntityReference]
GO
/****** Object:  ForeignKey [FK_Attribute_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Attribute]  WITH CHECK ADD  CONSTRAINT [FK_Attribute_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[Attribute] CHECK CONSTRAINT [FK_Attribute_Project]
GO
/****** Object:  ForeignKey [FK_Attribute_ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Attribute]  WITH CHECK ADD  CONSTRAINT [FK_Attribute_ProjectVersion] FOREIGN KEY([ProjectVersionId])
REFERENCES [Data].[ProjectVersion] ([ProjectVersionId])
GO
ALTER TABLE [Data].[Attribute] CHECK CONSTRAINT [FK_Attribute_ProjectVersion]
GO
/****** Object:  ForeignKey [FK_Method_Entity]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Method]  WITH CHECK ADD  CONSTRAINT [FK_Method_Entity] FOREIGN KEY([ObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Method] CHECK CONSTRAINT [FK_Method_Entity]
GO
/****** Object:  ForeignKey [FK_Method_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Method]  WITH CHECK ADD  CONSTRAINT [FK_Method_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[Method] CHECK CONSTRAINT [FK_Method_Project]
GO
/****** Object:  ForeignKey [FK_Method_ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Method]  WITH CHECK ADD  CONSTRAINT [FK_Method_ProjectVersion] FOREIGN KEY([ProjectVersionId])
REFERENCES [Data].[ProjectVersion] ([ProjectVersionId])
GO
ALTER TABLE [Data].[Method] CHECK CONSTRAINT [FK_Method_ProjectVersion]
GO
/****** Object:  ForeignKey [FK_Embaddable_ObjectDefination]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Embaddable]  WITH CHECK ADD  CONSTRAINT [FK_Embaddable_ObjectDefination] FOREIGN KEY([ObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Embaddable] CHECK CONSTRAINT [FK_Embaddable_ObjectDefination]
GO
/****** Object:  ForeignKey [FK_Embaddable_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Embaddable]  WITH CHECK ADD  CONSTRAINT [FK_Embaddable_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[Embaddable] CHECK CONSTRAINT [FK_Embaddable_Project]
GO
/****** Object:  ForeignKey [FK_Embaddable_ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Embaddable]  WITH CHECK ADD  CONSTRAINT [FK_Embaddable_ProjectVersion] FOREIGN KEY([ProjectVersionId])
REFERENCES [Data].[ProjectVersion] ([ProjectVersionId])
GO
ALTER TABLE [Data].[Embaddable] CHECK CONSTRAINT [FK_Embaddable_ProjectVersion]
GO
/****** Object:  ForeignKey [FK_Relationship_Attribute]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_Attribute] FOREIGN KEY([ParentAttributeId])
REFERENCES [Data].[Attribute] ([AttributeId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_Attribute]
GO
/****** Object:  ForeignKey [FK_Relationship_Attribute1]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_Attribute1] FOREIGN KEY([ChildAttributeId])
REFERENCES [Data].[Attribute] ([AttributeId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_Attribute1]
GO
/****** Object:  ForeignKey [FK_Relationship_Entity]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_Entity] FOREIGN KEY([ParentObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_Entity]
GO
/****** Object:  ForeignKey [FK_Relationship_Entity1]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_Entity1] FOREIGN KEY([ChildObjectId])
REFERENCES [Data].[ObjectDefination] ([ObjectId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_Entity1]
GO
/****** Object:  ForeignKey [FK_Relationship_Project]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_Project] FOREIGN KEY([ProjectId])
REFERENCES [Data].[Project] ([ProjectId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_Project]
GO
/****** Object:  ForeignKey [FK_Relationship_ProjectVersion]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[Relationship]  WITH CHECK ADD  CONSTRAINT [FK_Relationship_ProjectVersion] FOREIGN KEY([ProjectVersionId])
REFERENCES [Data].[ProjectVersion] ([ProjectVersionId])
GO
ALTER TABLE [Data].[Relationship] CHECK CONSTRAINT [FK_Relationship_ProjectVersion]
GO
/****** Object:  ForeignKey [FK_MethodParameter_Attribute]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[MethodParameter]  WITH CHECK ADD  CONSTRAINT [FK_MethodParameter_Attribute] FOREIGN KEY([AttributeId])
REFERENCES [Data].[Attribute] ([AttributeId])
GO
ALTER TABLE [Data].[MethodParameter] CHECK CONSTRAINT [FK_MethodParameter_Attribute]
GO
/****** Object:  ForeignKey [FK_MethodParameter_Method]    Script Date: 07/11/2014 17:36:41 ******/
ALTER TABLE [Data].[MethodParameter]  WITH CHECK ADD  CONSTRAINT [FK_MethodParameter_Method] FOREIGN KEY([MethodId])
REFERENCES [Data].[Method] ([MethodId])
GO
ALTER TABLE [Data].[MethodParameter] CHECK CONSTRAINT [FK_MethodParameter_Method]
GO
