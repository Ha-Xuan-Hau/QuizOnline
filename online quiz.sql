create database OnlineQuiz
GO
use OnlineQuiz
GO

create table [Role]	(
	[RoleId] int primary key,
	[Role] nvarchar(20)
);
create table [User] (
	[AccountId] int identity  primary key,
	[Username] nvarchar(50) not null,
	[Email] nvarchar(100),
	[Password] nvarchar(100) not null,
	[RoleId] int references [Role](RoleId),
	[IsActive] bit default 1
	);
create table [Student](
	[AccountId] int references [User](AccountId) on delete CASCADE,
	[StudentName] nvarchar(50) not null,
	[Phone] varchar(10),
	[DoB] date,
	Primary Key(AccountId)
);
create table [Teacher](
	[AccountId] int references [User](AccountId) on delete CASCADE,
	[TeacherName] nvarchar(50) not null,
	[Phone] varchar(10),
	Primary Key(AccountId)
	)
create table [Admin]	(
	AccountId int references [User](AccountId) on delete CASCADE,
	[AdminName] nvarchar(50) not null,
	[Phone] varchar(10),
	Primary key (AccountId)
	)
create table [Class](
	[ClassId] int identity primary key,
	[ClassName] nvarchar(100),
	[TeacherAccountId] int references [Teacher](AccountId),
	[CreateDate] date
	)
create table [Subject](
	[SubjectId] int identity primary key,
	[SubjectCode] nvarchar(10),
	[SubjectName] nvarchar(50)
	)
create table [Exam](
	[ExamId] int identity primary key	,
	[ClassId] int references [Class](ClassId),
	[TeacherAccountId] int ,
	[Title] nvarchar(50) not null,
	[Summary] nvarchar(100) not null,
	[Score] decimal(5,2),
	[StartDate] Datetime,
	[EndDate] Datetime,
	[Timer] int,
	[TakingTimes] int ,
	[Permission] bit
	)
create table [NormalQuestion](
	[QuesId] int identity primary key,
	[Content] nvarchar(max)
	)
create table [NormalQuestionAnswer](
	[AnswerId] int identity primary key,
	[QuesId] int references [NormalQuestion](QuesId) on delete CASCADE,
	[Content] nvarchar(max),
	[Correct] bit,
	[Percent] decimal(5,2)
	)
create table [QuestionSet](
	[SetId] int identity primary key,
	[UserAccountId] int references [User](AccountId),
	[SubjectId] int references [Subject](SubjectId) on delete cascade, 
	[QuesId] int references [NormalQuestion](QuesId) on delete cascade,
	[SetVote] int 
 	)
create table UserSetSaved (
    UserId int,
    SetId int,
    Primary key (UserId, SetId),
    Foreign key (UserId) references [User] (AccountId),
    Foreign key (SetId) references QuestionSet (SetId) on delete cascade
);
create table [ClassQuestionSet](
	[ClassSetId] int identity primary key,
	[ClassId] int references [Class](ClassId) on delete cascade,
	[SetId] int references [QuestionSet](SetId) on delete cascade
	)
	
create table [TakeClass](
	[TakeClassId] int identity primary key,
	[StudentAccountId] int references [User](AccountId) on delete CASCADE,
	[ClassId] int references [Class](ClassId) on delete CASCADE
	)
create table [QuestionExam](
	[ExamId] int references [Exam](ExamId) on delete CASCADE,
	[QuesId] int identity primary key,
	[Content] nvarchar(max),
	[Score] decimal(5,2)
	)
create table [QuestionExamAnswer](
	[AnswerId] int identity primary key,
	[QuesId] int references [QuestionExam](QuesId) on delete CASCADE,
	[Content] nvarchar(max),
	[Correct] bit,
	[Percent] decimal(5,2)
	)
create table [TakeExam](
	[TakeExamId] int identity primary key,
	[StudentAccountId] int references [User](AccountId) on delete CASCADE,
	[ExamId] int references [Exam](ExamId) on delete CASCADE,
	[Status] nvarchar(20),	
	[Score] decimal(5,2),
	[StartDate] Datetime,
	[EndDate] Datetime
	)
create table [TakeAnswer](
	[TakeAnswerId] int identity primary key,
	[TakeExamId] int references [TakeExam](TakeExamId) on delete CASCADE,
	[QuesId] int references [QuestionExam](QuesId),
	[AnswerId] int references [QuestionExamAnswer](AnswerId)
	)
