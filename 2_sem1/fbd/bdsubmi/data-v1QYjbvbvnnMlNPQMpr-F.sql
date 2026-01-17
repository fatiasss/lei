IF EXISTS(SELECT 1 FROM sys.tables WHERE object_id = OBJECT_ID('Nutrition_Info'))
BEGIN;
    DROP TABLE [Nutrition_Info];
END;
GO

CREATE TABLE [Nutrition_Info] (
    [Nutrition_InfoID] INTEGER NOT NULL IDENTITY(1, 1),
    [Calories] INTEGER NULL,
    [Fat] INTEGER NULL,
    [Carbs] INTEGER NULL,
    [Protein] INTEGER NULL,
    PRIMARY KEY ([Nutrition_InfoID])
);
GO

INSERT INTO [Nutrition_Info] (Calories,Fat,Carbs,Protein)
VALUES
  (374,770,866,591),
  (642,639,523,559),
  (447,872,675,272),
  (719,459,36,767),
  (376,326,537,316),
  (224,35,568,596),
  (245,884,455,917),
  (515,87,532,80),
  (393,678,137,725),
  (234,319,897,603);
INSERT INTO [Nutrition_Info] (Calories,Fat,Carbs,Protein)
VALUES
  (802,219,836,611),
  (22,495,198,460),
  (572,405,725,406),
  (759,114,987,612),
  (695,452,844,513),
  (365,737,34,839),
  (143,767,804,793),
  (989,297,679,131),
  (810,33,632,423),
  (628,423,334,513);
INSERT INTO [Nutrition_Info] (Calories,Fat,Carbs,Protein)
VALUES
  (663,23,906,404),
  (501,58,291,674),
  (583,757,588,510),
  (351,101,293,572),
  (452,616,526,675),
  (623,143,681,521),
  (264,86,766,289),
  (195,746,500,90),
  (251,37,228,859),
  (689,832,833,408);
INSERT INTO [Nutrition_Info] (Calories,Fat,Carbs,Protein)
VALUES
  (557,111,828,285),
  (718,170,473,275),
  (329,12,67,721),
  (644,470,945,244),
  (826,860,471,844),
  (695,20,715,671),
  (600,439,394,964),
  (747,45,831,880),
  (751,951,475,884),
  (456,404,29,446);
INSERT INTO [Nutrition_Info] (Calories,Fat,Carbs,Protein)
VALUES
  (4,158,376,88),
  (644,742,216,682),
  (622,721,368,602),
  (980,648,429,274),
  (131,510,213,485),
  (137,12,932,89),
  (699,58,589,635),
  (279,863,348,850),
  (896,990,90,234),
  (840,173,497,497);
