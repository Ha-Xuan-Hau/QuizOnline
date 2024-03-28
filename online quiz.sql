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
	[CreateDate] date,
	[ClassCode] NVARCHAR(7)
	)
create table [Subject](
	[SubjectId] int identity primary key,
	[SubjectCode] nvarchar(10),
	[SubjectName] nvarchar(100)
	)
create table [Exam](
	[ExamId] int identity primary key	,
	[ClassId] int references [Class](ClassId),
	[TeacherAccountId] int ,
	[Title] nvarchar(50) not null,
	[Summary] nvarchar(max) not null,
	[Score] decimal(5,2),
	[StartDate] Datetime,
	[EndDate] Datetime,
	[Timer] int,
	[TakingTimes] int ,
	[Permission] bit
	)
create table [QuestionSet](
	[SetId] int identity primary key,
	[Title] nvarchar(max),
	[UserAccountId] int references [User](AccountId),
	[SubjectId] int references [Subject](SubjectId) on delete cascade, 
	[SetVote] int 
 	)
create table [NormalQuestion](
	[QuesId] int identity primary key,
	[Content] nvarchar(max),
	[SetId] int references [QuestionSet](SetId) on delete cascade
	)
create table [NormalQuestionAnswer](
	[AnswerId] int identity primary key,
	[QuesId] int references [NormalQuestion](QuesId) on delete CASCADE,
	[Content] nvarchar(max),
	[Correct] bit,
	[Percent] decimal(5,2)
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

CREATE TRIGGER [dbo].[GenerateClassCodeTrigger]
ON [OnlineQuiz].[dbo].[Class]
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE c
    SET ClassCode = LEFT(CONVERT(NVARCHAR(36), NEWID()), 7)
    FROM [OnlineQuiz].[dbo].[Class] c
    INNER JOIN inserted i ON c.ClassId = i.ClassId
    WHERE c.ClassCode IS NULL;
END;


--isnert into Role
INSERT INTO [dbo].[Role]([RoleId],[Role]) VALUES(1,'Student')
INSERT INTO [dbo].[Role]([RoleId],[Role]) VALUES(2,'Teacher')
INSERT INTO [dbo].[Role]([RoleId],[Role]) VALUES(3,'Admin')

--Insert into User 
INSERT INTO [dbo].[User]([Username],[Email],[Password],[RoleId],[IsActive]) VALUES('student1','hieuldhe176771@gmail.com','6a5aeb1ea832832a9969a562357994ba',1,1)
INSERT INTO [dbo].[Student]([AccountId],[StudentName],[Phone],[DoB])VALUES(1 ,N'Lê Đình Hiếu','0912345678',null)

INSERT INTO [dbo].[User]([Username],[Email],[Password],[RoleId],[IsActive]) VALUES('student2','hauhxhe173353@gmail.com','6a5aeb1ea832832a9969a562357994ba',1,1)
INSERT INTO [dbo].[Student]([AccountId],[StudentName],[Phone],[DoB])VALUES(2 ,N'Hạ Xuân Hậu','0912345678',null)

INSERT INTO [dbo].[User]([Username],[Email],[Password],[RoleId],[IsActive]) VALUES('student3','student3@gmail.com','6a5aeb1ea832832a9969a562357994ba',1,1)
INSERT INTO [dbo].[Student]([AccountId],[StudentName],[Phone],[DoB])VALUES(3 ,N'Đặng Trọng Quân','0912345678',null)

INSERT INTO [dbo].[User]([Username],[Email],[Password],[RoleId],[IsActive]) VALUES('admin','kienlvhe173114@fpt.edu.vn','6a5aeb1ea832832a9969a562357994ba',3,1)
INSERT INTO [dbo].[Admin]([AccountId],[AdminName],[Phone])VALUES(4,N'Lê Văn Kiên','0912345678')

INSERT INTO [dbo].[User]([Username],[Email],[Password],[RoleId],[IsActive]) VALUES('teacher','teacher@gmail.com','6a5aeb1ea832832a9969a562357994ba',2,1)
INSERT INTO [dbo].[Teacher]([AccountId],[TeacherName],[Phone])VALUES(5,N'Phạm Gia Bảo','0912345678')

--Insert into Class
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 1',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 2',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 3',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 4',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 5',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 6',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 7',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 8',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 9',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 10',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 11',5,'2024-03-28')
INSERT INTO [dbo].[Class]([ClassName],[TeacherAccountId],[CreateDate])VALUES(N'Sample Class 12',5,'2024-03-28')

--Insert into Subject
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SSL101c', 'Academic Skills for University Success')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('WED201c', 'Web Design')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRF192', 'Programming Fundamentals')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MAS291', 'Statistics and Probability')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MAE101', 'Mathematics for Engineering')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('JPD123', 'Elementary Japanese 1-A1.2')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('CEA201', 'Computer Organization and Architecture')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('IOT102', 'Internet of Things')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('CSI104', 'Introduction to Computer Science')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRJ301', 'Java Web Application Development')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRO192', 'Object-Oriented Programming')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SWE201c', 'Introduction to Software Engineering')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MAD101', 'Discrete mathematics')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SWP391', 'Application development project')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('OSG202', 'Operating Systems')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('ITE302c', 'Ethics in IT')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('NWC203c', 'Computer Networking')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SWR302', 'Software Requirement')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SSG104', 'Communication and In-Group Working Skills')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SWT301', 'Software Testing')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('JPD113', 'Elementary Japanese 1-A1.1')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRN211', 'Basic Cross-Platform Application Programming With ''.NET''')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('CSD201', 'Data Structures and Algorithms')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('OJT202', 'On the job training')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('DBI202', 'Introduction to Databases')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('ENW492c', 'Writing Research Papers')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('LAB211', 'OOP with Java Lab')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRN221', 'Advanced Cross-Platform Application Programming With ''.NET''');
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('EXE101','Experiential Entrepreneurship 1')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('WDU203c','UI/UX Design')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRM392','Mobile Programming')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRN231','Building Cross-Platform Back-End Application With ''.NET''')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PRU211m','C# Programming and Unity')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MLN111','Philosophy of Marxism  Leninism')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SWD392','SW Architecture and Design')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MLN131','Scientific socialism')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('EXE201','Experiential Entrepreneurship 2')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('VNR202','History of Viet Nam Communist Party')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('MLN122','Political economics of Marxism  Leninism')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('SEP490','SE Capstone Project')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('PMG202c','Project management')
INSERT INTO [dbo].[Subject]([SubjectCode],[SubjectName])VALUES('HCM202','Ho Chi Minh Ideology')

--Insert into TakeClass
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(1,1)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(2,1)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(3,1)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(1,2)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(2,2)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(3,2)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(1,3)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(2,3)
INSERT INTO [dbo].[TakeClass]([StudentAccountId],[ClassId])VALUES(3,3)

--Insert into Exam
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(1,5,'Progress test 1',N'Kiến thức chương I',10,'2024-03-28','2024-03-30',1800,3,1)
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(1,5,'Progress test 2',N'Kiến thức chương II',10,'2024-03-20 08:00:00.000','2024-03-20 08:30:00.000',1800,3,1)
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(1,5,'Progress test 3',N'Kiến thức chương III',10,'2024-03-28','2024-03-30',1800,3,1)
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(2,5,'Progress test 1',N'Kiến thức chương I',10,'2024-03-28','2024-03-30',1800,3,1)
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(2,5,'Progress test 2',N'Kiến thức chương II',10,'2024-03-28','2024-03-30',1800,3,1)
INSERT INTO [dbo].[Exam]([ClassId],[TeacherAccountId],[Title],[Summary],[Score],[StartDate],[EndDate],[Timer],[TakingTimes],[Permission])VALUES(2,5,'Progress test 3',N'Kiến thức chương III',10,'2024-03-28','2024-03-30',1800,3,1)

--Insert  QuestionSet
INSERT INTO [dbo].[QuestionSet]([Title],[UserAccountId],[SubjectId],[SetVote])VALUES('SWT301 - 28/03',5 ,20,0)

INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Given the following state transition table Which of the test cases below will cover the following series of state transitions? S1 SO S1 S2 SO Exhibit:',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Which of the following is a MAJOR task of test planning?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Which of the following BEST describes the difference between an inspection and a walkthrough?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Where may functional testing be performed?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('What is the MAIN objective when reviewing a software deliverable? A. To identify potential application failures by use of a test specification.',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES(' Who would USUALLY perform debugging activities?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Which of the following would you NOT usually find on a software incident report?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES(' Some tools are geared more for developer use. For the 5 tools listed, which statement BEST details those for developers i) Performance testing tools. ii) Coverage measurement tools. iii) Test comparators. iv) Dynamic analysis tools. v) Incident management tools. ',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('Which of the following is correct?',1)
INSERT INTO [dbo].[NormalQuestion]([Content],[SetId])VALUES('As part of which test process do you determine the exit criteria?',1)

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(1,'A. D, A, B, C.',1 ,100.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(1,'B. A, B, C, D.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(1,'C. D, A, B.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(1,'D. A, B, C.',0 ,0.00)

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(2,'A. Scheduling test analysis and design tasks.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(2,'B. Initiating corrective actions.',1 ,100.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(2,'C. Monitoring progress and test coverage.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(2,'D. Measuring and analyzing results.',0 ,0.00)

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(3,'A. Both inspections and walkthroughs are led by the author.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(3,'B. An inspection is led by a moderator and a walkthrough is led by the author.',1 ,100.00 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(3,'C. Both inspections and walkthroughs are led by a trained moderator.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(3,'D. A walkthrough is led by the author. The author is not present during inspections.',0 ,0.00)

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(4,'A. At system and acceptance testing levels only.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(4,'B. At all test levels.',1 ,100.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(4,'C. At all levels above integration testing.',0 ,0.00)
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(4,'D. At the acceptance testing level only.',0 ,0.00)

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(5,'A. Test planning.',1 ,100.00 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(5,'B. Evaluating exit criteria and reporting.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(5,'C. Test closure.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(5,'D. Test control.',0 ,0 )

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(6,'A. To identify potential application failures by use of a test specification.',1 ,100 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(6,'B. To identify defects in any software work product.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(6,'C. To identify spelling mistakes in a requirements specification.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(6,'D. To identify standards inconsistencies in the code.',0 ,0 )

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(7,'A. Developers.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(7,'B. Analysts.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(7,'C. Testers.',1 ,100.00 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(7,'D. Incident Managers.',0 ,0 )

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(8,'A. Test case specification.',1 ,100 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(8,'B. Test design specification.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(8,'C. Test procedure specification.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(8,'D. Test results.',0 ,0 )

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(9,'A. i, iii. and iv. are more for developers.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(9,'B. ii. and iv. are more for developers.',1 ,100 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(9,'C. ii, iii. and iv. are more for developers.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(9,'D. ii. and iii. are more for developers.',0 ,0 )

INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(10,'A. Impact analysis assesses the effect on the system of a defect found in regression testing.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(10,'B. Impact analysis assesses the effect of a new person joining the regression test team.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(10,'C. Impact analysis assesses whether or not a defect found in regression testing has been fixed correctly.',0 ,0 )
INSERT INTO [dbo].[NormalQuestionAnswer]([QuesId],[Content],[Correct] ,[Percent])VALUES(10,'D. Impact analysis assesses the effect of a change to the system to determine how much regression testing to do.',1 ,100 )


--Insert into QuestionExam
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Given the following state transition table Which of the test cases below will cover the following series of state transitions? S1 SO S1 S2 SO Exhibit:',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Which of the following is a MAJOR task of test planning?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Which of the following BEST describes the difference between an inspection and a walkthrough?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Where may functional testing be performed?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'What is the MAIN objective when reviewing a software deliverable? A. To identify potential application failures by use of a test specification.',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Who would USUALLY perform debugging activities?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Which of the following would you NOT usually find on a software incident report?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Some tools are geared more for developer use. For the 5 tools listed, which statement BEST details those for developers i) Performance testing tools. ii) Coverage measurement tools. iii) Test comparators. iv) Dynamic analysis tools. v) Incident management tools. ',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'Which of the following is correct?',1.0)
INSERT INTO [dbo].[QuestionExam]([ExamId],[Content],[Score])VALUES (1,'As part of which test process do you determine the exit criteria?',1.0)

--Insert into QuestionExamAnswer
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(1,'A. D, A, B, C.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(1,'B. A, B, C, D.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(1,'C. D, A, B.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(1,'D. A, B, C.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(2,'A. Scheduling test analysis and design tasks.',1, 100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(2,'B. Initiating corrective actions.',0, 0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(2,'C. Monitoring progress and test coverage.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(2,'D. Measuring and analyzing results.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(3,'A. Both inspections and walkthroughs are led by the author.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(3,'B. An inspection is led by a moderator and a walkthrough is led by the author.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(3,'C. Both inspections and walkthroughs are led by a trained moderator.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(3,'D. A walkthrough is led by the author. The author is not present during inspections.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(4,'A. At system and acceptance testing levels only.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(4,'B. At all test levels.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(4,'C. At all levels above integration testing.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(4,'D. At the acceptance testing level only.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(5,'A. To identify potential application failures by use of a test specification.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(5,'B. To identify defects in any software work product.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(5,'C. To identify spelling mistakes in a requirements specification.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(5,'D. To identify standards inconsistencies in the code.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(6,'A. Developers.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(6,'B. Analysts.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(6,'C. Testers.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(6,'D. Incident Managers.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(7,'A. Test case specification.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(7,'B. Test design specification.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(7,'C. Test procedure specification.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(7,'D. Test results.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(8,'A. i, iii. and iv. are more for developers.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(8,'B. ii. and iv. are more for developers.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(8,'C. ii, iii. and iv. are more for developers.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(8,'D. ii. and iii. are more for developers.',0,0.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(9,'A. Impact analysis assesses the effect on the system of a defect found in regression testing.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(9,'B. Impact analysis assesses the effect of a new person joining the regression test team.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(9,'C. Impact analysis assesses whether or not a defect found in regression testing has been fixed correctly.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(9,'D. Impact analysis assesses the effect of a change to the system to determine how much regression testing to do.',1,100.00)

INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(10,'A. Test planning.',1,100.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(10,'B. Evaluating exit criteria and reporting.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(10,'C. Test closure.',0,0.00)
INSERT INTO [dbo].[QuestionExamAnswer]([QuesId],[Content],[Correct],[Percent])VALUES(10,'D. Test control.',0,0.00)






