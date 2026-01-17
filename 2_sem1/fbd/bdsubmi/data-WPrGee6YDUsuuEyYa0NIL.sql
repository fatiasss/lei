DROP TABLE IF EXISTS `Nutrition_Info`;

CREATE TABLE `Nutrition_Info` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `ID` mediumint,
  `Calories` mediumint default NULL,
  `Fat` mediumint default NULL,
  `Carbs` mediumint default NULL,
  `Protein` mediumint default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `Nutrition_Info` (`ID`,`Calories`,`Fat`,`Carbs`,`Protein`)
VALUES
  (1,849,363,907,595),
  (2,829,828,528,297),
  (3,976,97,194,533),
  (4,889,329,911,547),
  (5,867,240,155,935),
  (6,298,751,214,422),
  (7,427,530,525,987),
  (8,151,780,332,723),
  (9,148,446,614,479),
  (10,40,258,588,752);
INSERT INTO `Nutrition_Info` (`ID`,`Calories`,`Fat`,`Carbs`,`Protein`)
VALUES
  (11,454,9,256,639),
  (12,370,472,417,720),
  (13,503,442,234,222),
  (14,769,470,859,542),
  (15,494,42,246,304),
  (16,17,521,843,506),
  (17,357,691,427,106),
  (18,615,486,495,463),
  (19,468,371,571,369),
  (20,667,291,89,624);
INSERT INTO `Nutrition_Info` (`ID`,`Calories`,`Fat`,`Carbs`,`Protein`)
VALUES
  (21,860,10,906,927),
  (22,58,762,465,793),
  (23,559,781,867,305),
  (24,341,614,504,596),
  (25,948,432,671,479),
  (26,814,568,719,859),
  (27,292,881,215,885),
  (28,551,561,531,896),
  (29,417,541,351,531),
  (30,575,719,801,724);
INSERT INTO `Nutrition_Info` (`ID`,`Calories`,`Fat`,`Carbs`,`Protein`)
VALUES
  (31,879,574,163,529),
  (32,143,466,680,870),
  (33,768,429,356,197),
  (34,831,18,893,927),
  (35,447,245,903,314),
  (36,928,242,653,595),
  (37,56,429,382,927),
  (38,491,120,768,380),
  (39,458,911,182,599),
  (40,972,561,440,108);
INSERT INTO `Nutrition_Info` (`ID`,`Calories`,`Fat`,`Carbs`,`Protein`)
VALUES
  (41,382,350,413,525),
  (42,736,819,974,719),
  (43,407,610,112,421),
  (44,932,452,207,736),
  (45,81,55,42,549),
  (46,875,675,116,159),
  (47,404,691,147,775),
  (48,449,537,855,550),
  (49,447,657,783,541),
  (50,446,146,58,927);
