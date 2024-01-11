--create database Fpoly_DATN
--
--drop database Fpoly_DATN
go
use Fpoly_DATN

-- Thêm dữ liệu
go
INSERT INTO categories (id, name) VALUES ('CT001', 'Naruto')
INSERT INTO categories (id, name) VALUES ('CT002', 'One Piece')
INSERT INTO categories (id, name) VALUES ('CT003', 'Kimetsu No Yaiba')
INSERT INTO categories (id, name) VALUES ('CT004', 'Dragon Ball')

Go
INSERT INTO products (name, main_image, price, quantity, date_import, categoryid,sale,warranty_period,description) 
VALUES 
(N'Mô Hình ACE Dang Tay', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-dang-tay-cao-18cm-nang-200gram-co-box-1-280x280.jpg', 289900, 4, '2022-11-09', 'CT002',0,7, N'Mô Hình Ace Dang Tay - Cao 18cm, nặng 200gram'),
(N'Mô Hình Ace và Yamato', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-va-yamato-dai-chien-cao-29cm-nang-2kg5-co-vo-hop-mau-10-280x280.jpg', 109900, 7, '2021-08-13', 'CT002',0,6, N'Mô Hình ACE và Yamato đại chiến – Cao 29cm nặng 2kg5 – Có vỏ hộp'),
(N'Mô Hình Ace Hỏa Quyền', 'https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-ace-hoa-quyen-dung-chi-tay-cao-30cm-rong-17cm-nang-1kg7-co-hop-1-280x280.jpg
', 69900, 3, '2021-08-18', 'CT002',0,3, N'Mô Hình ACE hỏa quyền đứng chỉ tay – Cao 30cm, rộng 17cm, nặng 1kg7'),
(N'Mô Hình Eustass Kid wano', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-eustass-kid-wano-sieu-ngau-cao-17cm-nang-250g-co-vo-hop-mau-9-280x280.jpg', 201900, 9, '2023-03-27', 'CT002',0,8, N'Mô Hình Eustass Kid wano siêu ngầu – Cao 17cm nặng 250g – Có vỏ hộp màu'),
(N'Mô Hình Kaido Dạng Nhân Rồng', 'https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-kaido-dang-lai-rong-dang-dung-sieu-dep-cao-33cm-nang-4kg-co-hop-1-280x280.jpg', 121900, 10, '2019-11-22', 'CT002',0,12, N'Mô Hình Kaido dạng lai rồng dáng đứng siêu đẹp – Cao 33cm, nặng 4kg – Có Hộp'),
(N'Mô Hình Luffy vs Kaido', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-dai-chien-kaido-cao-39cm-nang-5kg-co-vo-hop-1-280x280.jpg', 131900, 8, '2019-12-09', 'CT002',0,31 ,N'Mô Hình Luffy đại chiến Kaido – Cao 39cm, nặng 5kg – Có vỏ hộp'),
(N'Mô Hình Law', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-law-trang-thai-chien-dau-sieu-dep-cao-41cm-nang-1kg1-co-hop-7-280x280.jpg', 121900, 2, '2020-01-09', 'CT002',0,12 ,N'Mô Hình Law trạng thái chiến đấu siêu đẹp – Cao 41cm, nặng 1kg1 – Có Hộp'),
(N'Mô Hình Luffy Cầm Áo', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-cam-ao-cao-28cm-nang-800gr-co-hop-1-280x280.jpg', 291900, 4, '2023-01-02', 'CT002',0,13 ,N'Mô Hình Luffy cầm áo – Cao 28cm, nặng 800gr – Có hộp'),
(N'Mô Hình Luffy Gear 2', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-2-dang-dung-sieu-ngau-led-usb-cao-40cm-nang-2kg5-co-hop-8-280x280.jpg', 352900, 2, '2023-02-17', 'CT002',0,2 , N'Mô Hình Luffy gear 2 dáng đứng siêu ngầu led usb – Cao 40cm nặng 2kg5 – Có hộp'),
(N'Mô Hình Luffy Gear 3', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-3-red-rock-cao-26cm-nang-1kg5-co-hop-1-280x280.jpg', 552900, 3, '2023-02-20', 'CT002',0,25 , N'Mô Hình Luffy gear 3 red Rock – Cao 26cm nặng 1kg5 – Có hộp'),
(N'Mô Hình Luffy Gear 5', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-5-cam-set-chien-dau-cao-26cm-nang-880gr-co-hop-9-280x280.jpg', 542900, 2, '2023-02-22', 'CT002',0,1 , N'Mô Hình Luffy gear 5 cầm sét chiến đấu – Cao 26cm nặng 880gr – Có hộp'),
(N'Mô Hình Luffy Gear 4 vs Kaido', 'https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-luffy-gear-4-dai-chien-kaido-cao-26cm-nang-2kg2-co-hop-9-280x280.jpg', 172900, 10, '2022-10-01', 'CT002',0,12 , N'Mô Hình Luffy Gear 4 đại chiến Kaido – Cao 26cm, nặng 2kg2 – Có hộp'),
(N'Mô Hình Nami cầm gậy', 'https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-nami-cam-gay-cao-40cm-nang-850gram-co-hop-dep-1-280x280.jpg', 232000, 2, '2019-08-12', 'CT002',0,11 , N'Mô Hình Nami cầm gậy – Cao 40cm, nặng 850gram – Có hộp đẹp'),
(N'Mô Hình Zoro Asura', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-asura-3-dau-6-tay-sieu-ngau-cao-38cm-nang-2kg1-hop-mau-1-280x280.jpg', 394500, 2, '2019-01-29', 'CT002',0,2 , N'Mô Hình Zoro Asura 3 đầu 6 tay siêu ngầu – Cao 38cm, nặng 2kg1 – Hộp màu'),
(N'Mô Hình Zoro Wano', 'https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-wano-hieu-ung-sieu-dep-cao-31cm-nang-3kg-co-hop-carton-4-280x280.jpg', 299000, 2, '2019-01-29', 'CT002',0,8 , N'Mô Hình Zoro wano hiệu ứng siêu đẹp – Cao 31cm, nặng 3kg – Có Hộp Carton'),
(N'Mô Hình Enel Chúa Trời', 'https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Enel-Chua-troi-sieu-pham-10.jpg', 262000, 2, '2021-02-23', 'CT002',0,11 , N'Mô Hình Enel Chúa Trời siêu phẩm - Cao 30cm nặng 3kg – Có Hộp'),
(N'Mô Hình Nami Wano', 'https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Nami-cao-cap-12.jpg', 182000, 8, '2019-12-29', 'CT002',0,9, N'Hàng Cao Cấp – Mô Hình Nami dáng đứng siêu quyến rũ cao 44cm rộng 22cm nặng 1500gram có hộp'),
(N'Mô Hình Chopper', 'https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Chopper-chien-dau-7-280x280.jpg', 142000, 8, '2020-05-29', 'CT002',0,5 , N'Mô Hình Chopper chiến đấu – Cao 28 cm, nặng 1kg – Có Hộp màu'),
(N'Mô Hình Doflamingo', 'https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Doflamingo-dang-dung-kieu-hanh-1-280x280.jpg', 122000, 7, '2021-03-17', 'CT002',0,4 , N'Mô Hình Doflamingo dáng đứng kiêu hãnh cao 48cm – nặng 2.2kg – Có Hộp Carton'),
(N'Mô Hình Luffy Gear 4', 'https://mohinhfigure.com/wp-content/uploads/2023/07/Hang-Loai-1-Mo-Hinh-Luffy-gear-4-king-fado-sieu-dep-1.jpg', 119000, 7, '2022-08-30', 'CT002',0,6 , N'Mô Hình Luffy gear 4 king fado siêu đẹp – Hàng loại 1 – Cao 33cm, nặng 2kg8 – Hộp màu đẹp ')


Go
INSERT INTO products (name, main_image, price, quantity, date_import, categoryid,sale,warranty_period,description) 
VALUES 
(N'Mô Hình ROS Pink Black Goku', 'https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-pink-black-goku-sieu-dep-cao-30cm-1-300x300.jpg', 289900, 4, '2022-11-09', 'CT004',0,2, N'Mô Hình ROS Pink Black Goku Siêu Đẹp Cao 30cm, Chất liệu PVC'),
(N'Mô Hình ROS Blue Gogeta', 'https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-blue-gogeta-sieu-dep-cao-32cm-1-280x280.jpg', 109900, 7, '2021-08-13', 'CT004',0,2, N'Mô Hình ROS Blue Gogeta Siêu Đẹp Cao 32cm, Chất liệu PVC'),
(N'Mô Hình Son Gohan Super Saiyan', 'https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-son-gohan-super-saiyan-kid-trang-tri-1-280x280.jpg', 699000, 3, '2021-08-18', 'CT004',0,4, N'Mô Hình Son Gohan Super Saiyan Kid Trang Trí, Chất liệu: PVC, Sản xuất: Hàng nội địa Trung Quốc'),
(N'Mô Hình MaBu tập chạy', 'https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-DragonBall-MaBu-tap-chay-sieu-de-thuong-cao-10cm-Figure-DragonBall-No-Box-108k-8-280x280.jpg', 2019, 9, '2023-03-27', 'CT004',0,4, N'Mô Hình MaBu tập chạy siêu dễ thương – Cao 10cm, nặng 120gr – No Box'),
(N'Mô hình Rồng Namek', 'https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-hinh-Rong-Nemek-Cao-36cm-hang-sieu-chat-Dragon-Ball-Co-Hop-Mau-818K-1-280x280.png', 121900, 10, '2019-11-22', 'CT004',0,4, N'Mô hình Rồng Namek siêu chất – Cao 36cm, nặng 4kg – Có Hộp Màu'),
(N'Mô Hình Vegeta kiêu ngạo', 'https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-vegeta-kieu-ngao-super-saiyan-xanh-vang-cao-29-cm-nang-300-gram-co-hop-dep-1-280x280.jpg', 1319, 8, '2019-12-09', 'CT004',0,4 ,N'Mô Hình Vegeta kiêu ngạo Super Saiyan Xanh – Vàng – Cao 29 cm – Nặng 300 Gram – Có Hộp Đẹp'),
(N'Mô Hình Majin Buu', 'https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-majin-buu-chien-dau-sieu-ngau-co-led-cao-21cm-nang-800gram-co-hop-mau-1-280x280.jpg', 121900, 2, '2020-01-09', 'CT004', 0,4,N'Mô Hình Majin Buu chiến đấu siêu ngầu có led cao 21cm – Nặng 800gram – Có Hộp màu'),
(N'Mô Hình Goku vs Vegeta', 'https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-Goku-dai-chien-Vegeta-sieu-dep-Cao-30cm-nang-2kg-1-280x280.jpg', 291900, 4, '2023-01-02', 'CT004',0,4, N'Mô Hình Goku đại chiến Vegeta siêu đẹp – Cao 30cm, nặng 2kg – Có Hộp bìa'),
(N'Mô hình Broly cáu giận', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-broly-cau-gian-hang-cuc-dep-cao-20cm-nang-500g-no-box-1.png', 35290, 2, '2023-02-17', 'CT004',0,4, N'Mô hình Broly cáu giận hàng cực đẹp – Cao 20cm, nặng 500gr – No Box'),
(N'Mô hình Vegata Blue', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/Mo-hinh-Vegata-Blue-Cao-20cm-nang-400gr-Co-Hop-Dep-8-280x280.png', 55290, 3, '2023-02-20', 'CT004',0,4, N'Mô hình Vegata Blue – Cao 20cm, nặng 400gr – Có Hộp Đẹp'),
(N'Mô Hình SonGoku SS4', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/Mo-Hinh-SonGoku-SS4-sieu-chat-Cao-28cm-nang-700g-Co-Hop-mau-1-768x768.jpg', 54290, 2, '2023-02-22', 'CT004',0,4, N'Mô Hình SonGoku SS4 siêu chất – Cao 28cm, nặng 700g – Có Hộp màu'),
(N'Mô Hình Cả Bộ 8 MaBu', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/Mo-Hinh-Ca-Bo-8-MaBu-nhieu-dang-bien-the-sieu-ngau-Cao-7-10cm-no-box-1.png', 172900, 10, '2022-10-01', 'CT004', 0,4,N'Mô Hình Cả Bộ 8 MaBu nhiều dạng biến thể siêu ngầu – Cao 7-10cm – no box, Chất liệu: PVC'),
(N'Mô Hình Frieza', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/Mo-Hinh-Frieza-sieu-ngau-trang-thai-chien-dau-co-led-Cao-25cm-nang-1kg1-Co-hop-1-768x768.jpg', 32000, 2, '2019-08-12', 'CT004',0,4, N'Mô Hình Frieza siêu ngầu trạng thái chiến đấu có led – Cao 25cm, nặng 1kg1 – Có hộp'),
(N'Mô Hình Songoku vô cực', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/hang-cao-cap-mo-hinh-songoku-vo-cuc-cao-33cm-nang-2kg-co-hop-mau-12-768x768.jpg', 39000, 2, '2019-01-29', 'CT004',0,4, N'Hàng Cao Cấp – Mô Hình Songoku vô cực – Cao 33cm, nặng 2kg – Có Hộp màu'),
(N'Mô hình SonGoku', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-songoku-nang-cau-sieu-ngau-cao-24cm-nang-900gr-co-hop-mau-9-768x768.jpg', 99000, 2, '2019-01-29', 'CT004',0,4, N'Mô hình SonGoku nâng cầu siêu ngầu – Cao 24cm, nặng 900gr – Có Hộp màu'),
(N'Mô Hình Goku trạng thái super saiyan', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-goku-trang-thai-super-saiyan-dung-tren-de-may-cao-23cm-nang-550gr-co-hop-mau-1-768x768.jpg', 62, 2, '2021-02-23', 'CT004',0,4, N'Mô Hình Goku trạng thái super saiyan đứng trên đế mây – Cao 23cm, nặng 550gr – Có Hộp màu'),
(N'Mô hình SonGoku 2', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-songoku-nang-cau-co-led-sieu-ngau-cao-23cm-nang-600gr-co-hop-mau-9-280x280.jpg', 82077, 8, '2019-12-29', 'CT004',0,4, N'Mô hình SonGoku nâng cầu có led siêu ngầu – Cao 23cm, nặng 600gr – Có Hộp màu'),
(N'Mô Hình Vegeta ném cầu', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-dragon-ball-vegeta-nem-cau-cao-17cm-nang-250gr-co-hop-mau-1-768x768.jpg', 14702, 8, '2020-05-29', 'CT004',0,4, N'Mô Hình Vegeta ném cầu – Cao 17cm, nặng 250gram – Có Hộp màu, Chất liệu: PVC'),
(N'Mô hình SonGoku bản năng vô cực', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-songoku-ban-nang-vo-cuc-co-led-cao-11cm-nang-500gr-co-hop-mau-1.png', 122770, 7, '2021-03-17', 'CT004',0,4, N'Mô hình SonGoku bản năng vô cực có led – Cao 11cm, nặng 500gr – Có Hộp Màu'),
(N'Mô Hình Goku Đại chiến Majin buu', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Dragon+Ball/mo-hinh-goku-dai-chien-majin-buu-co-led-o-cau-cao-21cm-nang-700gr-co-hop-mau-10-768x768.jpg', 119, 7, '2022-08-30', 'CT004',0,4, N'Mô Hình Goku Đại chiến Majin buu có led ở cầu – Cao 21cm, nặng 700gr – Có Hộp màu')

GO
INSERT INTO products (name, main_image, price, quantity, date_import, categoryid,sale,warranty_period,description) 
VALUES 
('Mô hình Itachi Akatsuki', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-itachi-akatsuki-de-hac-hoa-co-qua-tay-cam-non-cao-29cm-nang-820gr-co-hop-1-280x280.jpg', 289970, 4, '2020-12-09', 'CT001',0,2, N'Hắc Hỏa có quạ tay cầm nón'),
('Mô Hình Naruto Minato', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-naruto-minato-co-led-cao-22cm-nang-600gr-co-hop-mau-1-280x280.png', 345500, 4, '2019-01-01', 'CT001',0,2, N'Minato có LED Cao 22cm'),
('Mô hình Sasuke', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-sasuke-chien-dau-sieu-ngau-cao-18cm-nang-200gr-co-vo-hop-mau-1-280x280.jpg', 352200, 4, '2018-01-01', 'CT001',0,2, N'hiến đấu siêu ngầu'),
('Mô Hình Cữu Vĩ Kurama', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-cuu-vi-kurama-cao-16cm-nang-16kg-hop-mau-4-280x280.jpg', 192000, 4, '2017-01-01', 'CT001',0,2, N'Cao 16cm, nặng 1,6kg'),
('Mô hình Sasuke', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-sasuke-mac-kimono-sieu-to-cao-31cm-nang-1kg-co-hop-mau-1-280x280.png', 199900, 4, '2012-01-01', 'CT001',0,2, N'mặc kimono siêu to'),
('Mô hình Bán Thân Uchiha itachi', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/Uchiha-Itachi-1-280x280.jpg', 201000, 4, '2020-01-01', 'CT001',0,2, N'Cao 14cm, nặng 200gr'),
('Mô hình Bán Thân Uchiha itachi', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/Uchiha-Itachi-1-280x280.jpg', 254000, 4, '2020-01-01', 'CT001',0,2, N'Cao 14cm, nặng 200gr'),
('Mô Hình Might Guy', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-might-guy-sensei-bat-mon-co-led-cao-30cm-trang-tri-ban-hoc-1-280x280.jpg', 3500, 0, '2019-01-01', 'CT001',0,2, N'Sensei Bát Môn'),
('Mô Hình Uchiha Sasuke', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/screenshot_1588596264-280x280.png', 410050, 0, '2016-01-01', 'CT001',0,2, N'Sensei Bát Môn'),
('Mô Hình Naruto – Uchiha Sasuke', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/1667295715891-280x280.jpg', 801100, 0, '2015-01-01', 'CT001', 0,2,N'Sensei Bát Môn'),
('Mô Hình Uchiha Obito', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-uchiha-obito-dang-bien-doi-adn-cao-25cm-trang-tri-280x280.jpg', 2300, 0, '2014-01-01', 'CT001',0,2, N'Dạng Biến Đổi ADN '),
('Mô Hình Uchiha Madara', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-uchiha-madara-ban-tay-linh-hon-1-280x280.jpg', 450000, 0, '2013-01-01', 'CT001',0,2, N'Susano Bàn Tay Linh '),
('Mô Hình Uzumaki Naruto', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-thap-uzumaki-naruto-cao-30cm-trang-tri-ban-lam-viec-1-280x280.jpg', 129888, 0, '2012-01-01', 'CT001', 0,2,N'Cao 30cm Trang Trí Bàn Làm Việc '),
('Mô Hình Hatake Kakashi', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-luc-hatake-kakashi-30cm-trang-tri-ban-hoc-1-280x280.jpg', 121900, 0, '2011-01-01', 'CT001',0,2, N'30cm Trang Trí Bàn Học'),
('Mô Hình Tsunade Senju', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-ngu-tsunade-senju-30cm-trang-tri-ban-hoc-4-280x280.jpg', 574030, 0, '2016-01-01', 'CT001',0,2, N'30cm Trang Trí bàn Học'),
('Mô Hình Minato Namikaze', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-tu-minato-namikaze-cao-30cm-qua-tang-ban-trai-3-280x280.jpg', 621400, 0, '2018-01-01', 'CT001',0,2, N'30cm Trang Trí bàn Học'),
('Mô Hình Tobirama Senju', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-nhi-tobirama-senju-cao-30cm-trang-tri-ban-hoc-5-280x280.jpg', 236100, 0, '2013-01-01', 'CT001',0,2, N'30cm Trang Trí bàn Học'),
('Mô Hình Hashirama Senju', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokage-de-nhat-hashirama-senju-cao-30cm-qua-tang-sinh-nhat-1-280x280.jpg', 125400, 0, '2011-01-01', 'CT001',0,2, N'30cm Trang Trí bàn Học'),
('Mô Hình Uchiha Obito', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-uchiha-obito-luc-dao-cao-27cm-trang-tri-ban-hoc-1-280x280.jpg', 98200, 0, '2018-01-01', 'CT001',0,2, N'30cm Trang Trí bàn Học'),
('Mô Hình Hiruzen Sarutobi', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Naruto/mo-hinh-hokaghe-de-tam-hiruzen-sarutobi-30cm-trang-tri-1-280x280.jpg', 146300, 0, '2011-01-01', 'CT001',0,2, N'Lục Đạo Cao 27cm Trang Trí'),

('Mô Hình Kamado Zenitsu', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-demon-slayer-kamado-zenitsu-ngoi-cao-8cm-nang-180g-co-hop-mau-7-280x280.jpg', 412700, 0, '2011-01-01', 'CT003',0,2, N'gồi – Cao 8cm, nặng 180gr'),
('Mô Hình Hà Trụ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-ha-tru-dang-ngoi-cao-14cm-nang-150gr-co-vo-hop-mau-1-280x280.jpg', 148600, 0, '2012-02-01', 'CT003',0,2, N'Cao 14cm, nặng 150gr'),
('Mô Hình Nezuko', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/z4806526477541-b28203cfabf61bd2fd3f0ac46ce536cb-280x280.jpg', 182300, 0, '2020-02-01', 'CT003',0,2, N'Cao 8cm, nặng 100gr'),
('Mô Hình Shinobu', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-shinobu-bat-buom-co-led-cao-31cm-nang-2kg-co-hop-10-280x280.jpg', 93200, 0, '2020-08-01', 'CT003',0,2, N'Cao 31cm, nặng 2kg'),
('Mô Hình Shinobu ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-shinobu-dung-tren-mai-sieu-dep-co-led-o-mat-trang-cao-28cm-nang-1kg2-co-hop-1-280x280.jpg', 432100, 0, '2021-01-01', 'CT003',0,2, N'Cao 28cm, nặng 1KG2'),
('Mô Hình Tanjiro  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-tanjiro-chien-dau-cao-9cm-nang-130gr-co-vo-hop-1-280x280+(1).jpg', 234600, 4, '2009-1-01', 'CT003',100000,2, N'Cao 9cm, nặng 130gr'),
('Mô Hình Tomioka Giyuu   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-thuy-tru-tomioka-giyuu-chibi-chien-dau-cao-10cm-nang-145gr-co-vo-hop-mau-1-280x280.jpg', 6483, 0, '2021-01-01', 'CT003', 0,2,N'Cao 10cm, nặng 145gr'),
('Mô Hình Tanjiro  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-tanjiro-chien-dau-cao-9cm-nang-130gr-co-vo-hop-1-280x280+(1).jpg', 238000, 0, '2013-1-01', 'CT003',2000,2, N'Cao 9cm, nặng 130gr'),
('Mô Hình Kokushibou   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-thuong-nhat-kokushibou-chien-dau-co-led-cao-30cm-nang-25kg-co-hop-mau-1-280x280.jpg', 123500, 0, '2021-01-01', 'CT003',0,2, N'Cao 30cm, nặng 2,5kg'),
('Mô Hình Tsugikuni Yoriichi  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/mo-hinh-tsugikuni-yoriichi-chien-dau-co-led-cao-30cm-nang-25kg-co-hop-mau-1-280x280.jpg', 285100, 0, '2021-01-01', 'CT003',0,2, N'Cao 30cm, nặng 2,5kg'),
('Mô Hình Luyến Trụ  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Mo-Hinh-Luyen-Tru-chien-dau-Cao-22cm-nang-800gr-Co-Vo-Hop-mau-1-280x280.jpg', 42000, 4, '2016-1-01', 'CT003',0,2, N'Cao 22cm, nặng 800gr'),
('Mô Hình Sabito   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Mo-Hinh-Sabito-dang-dung-sieu-ngau-cao-29cm-nang-700g-Co-Hop-mau-1-280x280.jpg', 69000, 0, '2018-1-01', 'CT003',0,2, N'Cao 29cm, nặng 700g'),
('Mô Hình Phong Trụ  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Mo-Hinh-Kimetsu-No-Yaiba-Phong-Tru-dang-dung-sieu-ngau-cao-31cm-nang-700g-Co-Hop-1-280x280.jpg', 29000, 0, '2019-1-01', 'CT003',0,2, N'Cao 31cm, nặng 700g '),
('Mô Hình Zenitshu   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Kimetsu-No-Yaiba-Zenitshu-chien-dau-co-led-sieu-dep-cao-35cm-nang-1kg2-Co-hop-mau-1-280x280.jpg', 12000, 0, '2011-1-01', 'CT003',0,2, N'Cao 35cm, nặng 1kg2'),
('Mô Hình Kokushibou   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Thuong-Nhat-Kokushibou-Cao-30cm-nang-500g-Full-box-1-280x280.jpg', 120003, 0, '2019-1-01', 'CT003', 0,2,N'Cao 30cm, nặng 500g'),
('Mô Hình Zenitsu   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Demon-Slayer-Agatsuma-Zenitsu-Cao-15cm-nang-600g-No-Box-7-280x280.png', 235000, 0, '2012-1-01', 'CT003',0,2, N'cao 15cm,nặng 600g'),
('Mô Hình Shinobu   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Kimetsu-No-Yaiba-Shinobu-dang-dung-chien-dau-cao-29cm-nang-800gr-Co-Hop-1-280x280.jpg', 77000, 0, '2019-1-01', 'CT003', 0,2, N'Cao 29cm, nặng 800g '),
('Mô Hình Âm Trụ  ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/Kimetsu-No-Yaiba-am-tru-dang-dung-sieu-ngau-cao-33cm-nang-500gr-1-280x280.jpg', 99000, 0, '2012-1-01', 'CT003', 0,2,N'Cao 33cm, nặng 500gr'),
('Mô Hình Inosuke   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/1667308552970-280x280.jpg', 11000, 0, '2012-1-01', 'CT003', 0,2,N'Cao 9cm, nặng 130gr'),
('Mô Hình Gyuutarou   ', 'https://my-app-datn.s3.ap-southeast-1.amazonaws.com/AnhWebsite/Yaiba/1667307842053-280x280.jpg', 12099, 0, '2013-1-01', 'CT003',0,2, N'Cao 30cm Nặng 500')

GO
INSERT INTO product_images(imageurl, productid)
Values ('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-dang-tay-cao-18cm-nang-200gram-co-box-1-280x280.jpg', 1),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-dang-tay-cao-18cm-nang-200gram-co-box-5-768x768.jpg', 1),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-dang-tay-cao-18cm-nang-200gram-co-box-4-768x768.jpg', 1),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-dang-tay-cao-18cm-nang-200gram-co-box-3-768x768.jpg', 1),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-va-yamato-dai-chien-cao-29cm-nang-2kg5-co-vo-hop-mau-10-280x280.jpg', 2),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-va-yamato-dai-chien-cao-29cm-nang-2kg5-co-vo-hop-mau-8-280x280.jpg', 2),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-va-yamato-dai-chien-cao-29cm-nang-2kg5-co-vo-hop-mau-7-768x768.jpg', 2),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-ace-va-yamato-dai-chien-cao-29cm-nang-2kg5-co-vo-hop-mau-6-768x768.jpg', 2),

		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-ace-hoa-quyen-dung-chi-tay-cao-30cm-rong-17cm-nang-1kg7-co-hop-1-280x280.jpg', 3),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-ace-hoa-quyen-dung-chi-tay-cao-30cm-rong-17cm-nang-1kg7-co-hop-8-768x768.jpg', 3),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-ace-hoa-quyen-dung-chi-tay-cao-30cm-rong-17cm-nang-1kg7-co-hop-7-768x768.jpg', 3),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-ace-hoa-quyen-dung-chi-tay-cao-30cm-rong-17cm-nang-1kg7-co-hop-6-768x768.jpg', 3),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-eustass-kid-wano-sieu-ngau-cao-17cm-nang-250g-co-vo-hop-mau-9-280x280.jpg', 4),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-eustass-kid-wano-sieu-ngau-cao-17cm-nang-250g-co-vo-hop-mau-9-768x768.jpg', 4),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-eustass-kid-wano-sieu-ngau-cao-17cm-nang-250g-co-vo-hop-mau-8-768x768.jpg', 4),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-eustass-kid-wano-sieu-ngau-cao-17cm-nang-250g-co-vo-hop-mau-7-768x768.jpg', 4),

		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-kaido-dang-lai-rong-dang-dung-sieu-dep-cao-33cm-nang-4kg-co-hop-1-280x280.jpg', 5),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-kaido-dang-lai-rong-dang-dung-sieu-dep-cao-33cm-nang-4kg-co-hop-10-768x768.jpg', 5),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-kaido-dang-lai-rong-dang-dung-sieu-dep-cao-33cm-nang-4kg-co-hop-9-768x768.jpg', 5),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-kaido-dang-lai-rong-dang-dung-sieu-dep-cao-33cm-nang-4kg-co-hop-8-768x768.jpg', 5),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-dai-chien-kaido-cao-39cm-nang-5kg-co-vo-hop-1-280x280.jpg', 6),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-dai-chien-kaido-cao-39cm-nang-5kg-co-vo-hop-9-768x768.jpg', 6),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-dai-chien-kaido-cao-39cm-nang-5kg-co-vo-hop-8-768x768.jpg', 6),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-dai-chien-kaido-cao-39cm-nang-5kg-co-vo-hop-2-768x768.jpg', 6),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-law-trang-thai-chien-dau-sieu-dep-cao-41cm-nang-1kg1-co-hop-7-280x280.jpg', 7),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-law-trang-thai-chien-dau-sieu-dep-cao-41cm-nang-1kg1-co-hop-5-768x768.jpg', 7),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-law-trang-thai-chien-dau-sieu-dep-cao-41cm-nang-1kg1-co-hop-4-768x768.jpg', 7),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-law-trang-thai-chien-dau-sieu-dep-cao-41cm-nang-1kg1-co-hop-3-768x768.jpg', 7),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-cam-ao-cao-28cm-nang-800gr-co-hop-1-280x280.jpg', 8),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-cam-ao-cao-28cm-nang-800gr-co-hop-8-768x768.jpg', 8),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-cam-ao-cao-28cm-nang-800gr-co-hop-2-768x768.jpg', 8),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-cam-ao-cao-28cm-nang-800gr-co-hop-3-768x768.jpg', 8)


GO
insert into [Fpoly_DATN].[dbo].[status](id, name)
values(0, N'Hủy đơn'),
		(1, N'Đang xác nhận'),
		(2, N'Đã xác nhận'),
		(3, N'Đang giao'),
		(4, N'Đã giao'),
		(5, N'Trả hàng')

GO
INSERT INTO product_images(imageurl, productid)
Values ('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-2-dang-dung-sieu-ngau-led-usb-cao-40cm-nang-2kg5-co-hop-8-280x280.jpg', 9),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-2-dang-dung-sieu-ngau-led-usb-cao-40cm-nang-2kg5-co-hop-5.jpg', 9),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-2-dang-dung-sieu-ngau-led-usb-cao-40cm-nang-2kg5-co-hop-2.jpg', 9),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-2-dang-dung-sieu-ngau-led-usb-cao-40cm-nang-2kg5-co-hop-1.jpg', 9),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-3-red-rock-cao-26cm-nang-1kg5-co-hop-1-280x280.jpg', 10),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-3-red-rock-cao-26cm-nang-1kg5-co-hop-3.jpg', 10),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-3-red-rock-cao-26cm-nang-1kg5-co-hop-4.jpg', 10),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-3-red-rock-cao-26cm-nang-1kg5-co-hop-2.jpg', 10),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-5-cam-set-chien-dau-cao-26cm-nang-880gr-co-hop-9-280x280.jpg', 11),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-5-cam-set-chien-dau-cao-26cm-nang-880gr-co-hop-8.jpg', 11),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-5-cam-set-chien-dau-cao-26cm-nang-880gr-co-hop-7.jpg', 11),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-luffy-gear-5-cam-set-chien-dau-cao-26cm-nang-880gr-co-hop-6.jpg', 11),

		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-luffy-gear-4-dai-chien-kaido-cao-26cm-nang-2kg2-co-hop-9-280x280.jpg', 12),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-luffy-gear-4-dai-chien-kaido-cao-26cm-nang-2kg2-co-hop-5.jpg', 12),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-luffy-gear-4-dai-chien-kaido-cao-26cm-nang-2kg2-co-hop-4.jpg', 12),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-luffy-gear-4-dai-chien-kaido-cao-26cm-nang-2kg2-co-hop-3.jpg', 12),

		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-nami-cam-gay-cao-40cm-nang-850gram-co-hop-dep-1-280x280.jpg', 13),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-nami-cam-gay-cao-40cm-nang-850gram-co-hop-dep-7.jpg', 13),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-nami-cam-gay-cao-40cm-nang-850gram-co-hop-dep-8.jpg', 13),
		('https://mohinhfigure.com/wp-content/uploads/2023/10/mo-hinh-nami-cam-gay-cao-40cm-nang-850gram-co-hop-dep-3.jpg', 13),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-asura-3-dau-6-tay-sieu-ngau-cao-38cm-nang-2kg1-hop-mau-1-280x280.jpg', 14),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-asura-3-dau-6-tay-sieu-ngau-cao-38cm-nang-2kg1-hop-mau-12.jpg', 14),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-asura-3-dau-6-tay-sieu-ngau-cao-38cm-nang-2kg1-hop-mau-10.jpg', 14),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-asura-3-dau-6-tay-sieu-ngau-cao-38cm-nang-2kg1-hop-mau-9.jpg', 14),

		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-wano-hieu-ung-sieu-dep-cao-31cm-nang-3kg-co-hop-carton-4-280x280.jpg', 15),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-wano-hieu-ung-sieu-dep-cao-31cm-nang-3kg-co-hop-carton-1.png', 15),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-wano-hieu-ung-sieu-dep-cao-31cm-nang-3kg-co-hop-carton-3.jpg', 15),
		('https://mohinhfigure.com/wp-content/uploads/2023/09/mo-hinh-zoro-wano-hieu-ung-sieu-dep-cao-31cm-nang-3kg-co-hop-carton-7.jpg', 15),

		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Enel-Chua-troi-sieu-pham-10.jpg', 16),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Enel-Chua-troi-sieu-pham-16.jpg', 16),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Enel-Chua-troi-sieu-pham-15.jpg', 16),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Enel-Chua-troi-sieu-pham-14.jpg', 16),

		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Nami-cao-cap-12.jpg', 17),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Nami-cao-cap-11.jpg', 17),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Nami-cao-cap-10.jpg', 17),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-hinh-Nami-cao-cap-9.jpg', 17),

		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Chopper-chien-dau-7-280x280.jpg', 18),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Chopper-chien-dau-6.jpg', 18),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Chopper-chien-dau-5.jpg', 18),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Chopper-chien-dau-4.jpg', 18),

		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Doflamingo-dang-dung-kieu-hanh-1-280x280.jpg', 19),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Doflamingo-dang-dung-kieu-hanh-9.jpg', 19),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Doflamingo-dang-dung-kieu-hanh-8.jpg', 19),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Mo-Hinh-Doflamingo-dang-dung-kieu-hanh-7.jpg', 19),

		('https://mohinhfigure.com/wp-content/uploads/2023/07/Hang-Loai-1-Mo-Hinh-Luffy-gear-4-king-fado-sieu-dep-1.jpg', 20),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Hang-Loai-1-Mo-Hinh-Luffy-gear-4-king-fado-sieu-dep-8.jpg', 20),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Hang-Loai-1-Mo-Hinh-Luffy-gear-4-king-fado-sieu-dep-7.jpg', 20),
		('https://mohinhfigure.com/wp-content/uploads/2023/07/Hang-Loai-1-Mo-Hinh-Luffy-gear-4-king-fado-sieu-dep-6.jpg', 20)

		Go 
INSERT INTO product_images(imageurl, productid)
Values ('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-pink-black-goku-sieu-dep-cao-30cm-1-300x300.jpg', 21),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-pink-black-goku-sieu-dep-cao-30cm-3-600x600.jpg', 21),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-pink-black-goku-sieu-dep-cao-30cm-4-600x600.jpg', 21),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-pink-black-goku-sieu-dep-cao-30cm-5-600x600.jpg', 21),

		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-blue-gogeta-sieu-dep-cao-32cm-1-280x280.jpg', 22),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-blue-gogeta-sieu-dep-cao-32cm-2-600x600.jpg', 22),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-ros-blue-gogeta-sieu-dep-cao-32cm-3-600x600.jpg', 22),

		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-son-gohan-super-saiyan-kid-trang-tri-1-280x280.jpg', 23),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-son-gohan-super-saiyan-kid-trang-tri-5-600x600.jpg', 23),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-son-gohan-super-saiyan-kid-trang-tri-4-600x600.jpg', 23),
		('https://mohinhfigure.com/wp-content/uploads/2021/08/mo-hinh-son-gohan-super-saiyan-kid-trang-tri-3-600x600.jpg', 23),

		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-DragonBall-MaBu-tap-chay-sieu-de-thuong-cao-10cm-Figure-DragonBall-No-Box-108k-8-280x280.jpg', 24),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-DragonBall-MaBu-tap-chay-sieu-de-thuong-cao-10cm-Figure-DragonBall-No-Box-108k-5-768x768.jpg', 24),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-DragonBall-MaBu-tap-chay-sieu-de-thuong-cao-10cm-Figure-DragonBall-No-Box-108k-4-768x768.jpg', 24),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-DragonBall-MaBu-tap-chay-sieu-de-thuong-cao-10cm-Figure-DragonBall-No-Box-108k-3-768x768.jpg', 24),

		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-hinh-Rong-Nemek-Cao-36cm-hang-sieu-chat-Dragon-Ball-Co-Hop-Mau-818K-1-280x280.png', 25),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-hinh-Rong-Nemek-Cao-36cm-hang-sieu-chat-Dragon-Ball-Co-Hop-Mau-818K-3.png', 25),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-hinh-Rong-Nemek-Cao-36cm-hang-sieu-chat-Dragon-Ball-Co-Hop-Mau-818K-2.png', 25),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/11-02fc4e59-cfa4-414c-8ad2-d0dda92501d4.png', 25),

		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-vegeta-kieu-ngao-super-saiyan-xanh-vang-cao-29-cm-nang-300-gram-co-hop-dep-1-280x280.jpg', 26),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-vegeta-kieu-ngao-super-saiyan-xanh-vang-cao-29-cm-nang-300-gram-co-hop-dep-8-768x768.jpg', 26),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-vegeta-kieu-ngao-super-saiyan-xanh-vang-cao-29-cm-nang-300-gram-co-hop-dep-7-768x768.jpg', 26),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-vegeta-kieu-ngao-super-saiyan-xanh-vang-cao-29-cm-nang-300-gram-co-hop-dep-4-768x768.jpg', 26),

		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-majin-buu-chien-dau-sieu-ngau-co-led-cao-21cm-nang-800gram-co-hop-mau-1-280x280.jpg', 27),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-majin-buu-chien-dau-sieu-ngau-co-led-cao-21cm-nang-800gram-co-hop-mau-6-600x600.jpg', 27),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-majin-buu-chien-dau-sieu-ngau-co-led-cao-21cm-nang-800gram-co-hop-mau-5-600x600.jpg', 27),
		('https://mohinhfigure.com/wp-content/uploads/2023/02/mo-hinh-majin-buu-chien-dau-sieu-ngau-co-led-cao-21cm-nang-800gram-co-hop-mau-4-600x600.jpg', 27),

		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-Goku-dai-chien-Vegeta-sieu-dep-Cao-30cm-nang-2kg-1-280x280.jpg', 28),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-Goku-dai-chien-Vegeta-sieu-dep-Cao-30cm-nang-2kg-7-768x768.jpg', 28),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-Goku-dai-chien-Vegeta-sieu-dep-Cao-30cm-nang-2kg-6-768x768.jpg', 28),
		('https://mohinhfigure.com/wp-content/uploads/2023/08/Mo-Hinh-Goku-dai-chien-Vegeta-sieu-dep-Cao-30cm-nang-2kg-4-768x768.jpg', 28)

go
INSERT INTO voucher(voucher_code, description, discount, start_date,  quantity, rate, review, end_date, status, total_requested)
VALUES
('VOUCHER12', N'Giảm giá 150.000đ cho đơn trên 350.000đ', 150000, '2023-12-01', 6, 5, N'Giảm 150K đơn 350K', '2024-12-01', 1, 350000),

('VOUCHER13', N'Giảm giá 500.000đ cho đơn trên 1.000.000đ', 500000, '2023-12-01', 4, 5, N'Giảm 500K đơn 1.000K', '2024-12-01', 1, 1000000),

  ('VOUCHER11', N'Giảm giá 50.000đ cho đơn trên 400.000đ', 50000, '2023-12-01', 10, 20, N'Giảm 50K đơn 400K', '2024-12-01', 1, 400000),

  ('VOUCHER02', N'Giảm giá 100.000đ cho đơn trên 300.000đ', 100000, '2023-12-01', 5, 15, N'Giảm 100k đơn 300K', '2024-02-15', 1, 300000),

  ('VOUCHER03', N'Giảm giá 100.000đ cho đơn 200.000đ', 100000, '2023-12-15', 4, 5, N'Giảm 100K đơn 200k', '2024-03-01', 1, 200000),

  ('VOUCHER04', N'Giảm giá 50.000đ cho đơn 500.000đ', 50000, '2023-12-15', 10, 20, N'Giảm 50K đơn 500K', '2024-10-15', 1, 500000), 

  ('VOUCHER05', N'Giảm giá 30.000đ cho đơn 200.000đ', 30000, '2023-12-15', 8, 15, N'Giảm 30K đơn 200K', '2024-03-01', 1, 200000),

  ('VOUCHER06', N'Giảm giá 60.000đ cho đơn 300.000đ', 60000, '2023-12-15', 10, 20, N'Giảm 60k đơn 300k', '2024-03-01', 1, 300000),

  ('VOUCHER07', N'Giảm giá 10.000đ cho đơn 200.000đ', 10000, '2023-12-15', 20, 20, N'Giảm 10K đơn 200K', '2024-03-01', 1, 200000),

  ('VOUCHER08', N'Giảm 35.000đ giá cho đơn 350.000đ', 35000, '2023-12-15', 10, 20, N'Giảm 35K đơn 350K', '2024-03-01', 1, 350000),

  ('VOUCHER09', N'Giảm giá 200.000đ cho đơn 400.000đ', 200000, '2023-12-15', 3, 5, N'Giảm 200k đơn 400k', '2024-03-01', 1, 400000),

  ('VOUCHER010', N'Giảm giá 20.000đ cho đơn 200.000đ', 20000, '2023-12-15', 10, 10, N'Giảm 20K đơn 200K', '2024-03-01', 1, 200000),

  ('0', N'Chúc bạn may mắn lần sau', 0, '2023-12-15', 9999, 40, N'Chúc may mắn lần sau!', '2024-03-01', 1, 0)
  ;

--insert into accounts values ('user2',2,null,null,'user2@gmail.com', null, null,N'Tuấn', '$2a$10$nmVe57x1.cOGQTm699iTgebH0IyJnSJkKNPTuN5Wsu58MtUZkTub.', null,null,null,0,N'Trần Thanh')
--insert into accounts values ('user3',2,null,null,'user3@gmail.com', null, null,N'Sang', '$2a$10$nmVe57x1.cOGQTm699iTgebH0IyJnSJkKNPTuN5Wsu58MtUZkTub.', null,null,null,0,N'Đặng Thanh')
--insert into accounts values ('user4',2,null,null,'user4@gmail.com', null, null,N'Long', '$2a$10$nmVe57x1.cOGQTm699iTgebH0IyJnSJkKNPTuN5Wsu58MtUZkTub.', null,null,null,0,N'Trần Thanh')
--insert into accounts values ('user5',2,null,null,'user5@gmail.com', null, null,N'Kiên', '$2a$10$nmVe57x1.cOGQTm699iTgebH0IyJnSJkKNPTuN5Wsu58MtUZkTub.', null,null,null,0,N'Nguyễn Trung')
--insert into accounts values ('user6',2,null,null,'user5@gmail.com', null, null,N'Thành', '$2a$10$nmVe57x1.cOGQTm699iTgebH0IyJnSJkKNPTuN5Wsu58MtUZkTub.', null,null,null,0,N'Nguyễn Huy')
--  select * from accounts

--  insert into authorities values ('user2', 'ROLE_USER')
--insert into authorities values ('user3', 'ROLE_USER')
--insert into authorities values ('user4', 'ROLE_USER')
--insert into authorities values ('user5', 'ROLE_USER')
--insert into authorities values ('user6', 'ROLE_USER')

  --Du lieu mau order
--  insert into orders values('aaaaa',100000,N'112 nguyễn kiệm','12-12-2023','0394087873','user1',null)
--insert into orders values('bbbbb',200000,N'112 nguyễn kiệm','11-11-2023','0394087873','user1',null)
--insert into orders values('ccccc',300000,N'112 nguyễn kiệm','12-10-2023','0394087873','user1',null)
--insert into orders values('ddddd',400000,N'112 nguyễn kiệm','09-09-2023','0394087873','user1',null)
--insert into orders values('eeeee',500000,N'112 nguyễn kiệm','08-08-2023','0394087873','user1',null)
--insert into orders values('fffff',100000,N'112 nguyễn kiệm','07-07-2023','0394087873','user1',null)
--insert into orders values('ggggg',200000,N'112 nguyễn kiệm','06-06-2023','0394087873','user1',null)
--insert into orders values('hhhhh',300000,N'112 nguyễn kiệm','05-05-2023','0394087873','user1',null)
--insert into orders values('iiiii',400000,N'112 nguyễn kiệm','04-04-2023','0394087873','user1',null)
--insert into orders values('mmmmm',500000,N'112 nguyễn kiệm','03-03-2023','0394087873','user1',null)
--insert into orders values('nnnnn',300000,N'112 nguyễn kiệm','02-02-2023','0394087873','user1',null)
--insert into orders values('ooooo',400000,N'112 nguyễn kiệm','01-01-2023','0394087873','user1',null)

--insert into orders values('1aaaa',100000,N'112 nguyễn kiệm','12-12-2022','0394087873','user1',null)
--insert into orders values('1bbbb',200000,N'112 nguyễn kiệm','11-11-2022','0394087873','user1',null)
--insert into orders values('1cccc',300000,N'112 nguyễn kiệm','10-10-2022','0394087873','user1',null)
--insert into orders values('1dddd',400000,N'112 nguyễn kiệm','09-09-2022','0394087873','user1',null)
--insert into orders values('1eeee',500000,N'112 nguyễn kiệm','08-08-2022','0394087873','user1',null)
--insert into orders values('1ffff',100000,N'112 nguyễn kiệm','07-07-2022','0394087873','user1',null)
--insert into orders values('1gggg',200000,N'112 nguyễn kiệm','06-06-2022','0394087873','user1',null)
--insert into orders values('1hhhh',300000,N'112 nguyễn kiệm','05-05-2022','0394087873','user1',null)
--insert into orders values('1iiii',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user1',null)
--insert into orders values('1mmmm',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user1',null)
--insert into orders values('1nnnn',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user1',null)
--insert into orders values('1oooo',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user1',null)

--insert into orders values('2aaaa',100000,N'112 nguyễn kiệm','12-12-2021','0394087873','user1',null)
--insert into orders values('2bbbb',200000,N'112 nguyễn kiệm','11-11-2021','0394087873','user1',null)
--insert into orders values('2cccc',300000,N'112 nguyễn kiệm','10-10-2021','0394087873','user1',null)
--insert into orders values('2dddd',400000,N'112 nguyễn kiệm','09-09-2021','0394087873','user1',null)
--insert into orders values('2eeee',500000,N'112 nguyễn kiệm','08-08-2021','0394087873','user1',null)
--insert into orders values('2ffff',100000,N'112 nguyễn kiệm','07-07-2021','0394087873','user1',null)
--insert into orders values('2gggg',200000,N'112 nguyễn kiệm','06-06-2021','0394087873','user1',null)
--insert into orders values('2hhhh',300000,N'112 nguyễn kiệm','05-05-2021','0394087873','user1',null)
--insert into orders values('2iiii',400000,N'112 nguyễn kiệm','04-04-2021','0394087873','user1',null)
--insert into orders values('2mmmm',500000,N'112 nguyễn kiệm','03-03-2021','0394087873','user1',null)
--insert into orders values('2nnnn',300000,N'112 nguyễn kiệm','02-02-2021','0394087873','user1',null)
--insert into orders values('2oooo',400000,N'112 nguyễn kiệm','01-01-2021','0394087873','user1',null)

--insert into orders values('3aaaa',100000,N'112 nguyễn kiệm','12-12-2020','0394087873','user1',null)
--insert into orders values('3bbbb',200000,N'112 nguyễn kiệm','11-11-2020','0394087873','user1',null)
--insert into orders values('3cccc',300000,N'112 nguyễn kiệm','10-10-2020','0394087873','user1',null)
--insert into orders values('3dddd',400000,N'112 nguyễn kiệm','09-09-2020','0394087873','user1',null)
--insert into orders values('3eeee',500000,N'112 nguyễn kiệm','08-08-2020','0394087873','user1',null)
--insert into orders values('3ffff',100000,N'112 nguyễn kiệm','07-07-2020','0394087873','user1',null)
--insert into orders values('3gggg',200000,N'112 nguyễn kiệm','06-06-2020','0394087873','user1',null)
--insert into orders values('3hhhh',300000,N'112 nguyễn kiệm','05-05-2020','0394087873','user1',null)
--insert into orders values('3iiii',400000,N'112 nguyễn kiệm','04-04-2020','0394087873','user1',null)
--insert into orders values('3mmmm',500000,N'112 nguyễn kiệm','03-03-2020','0394087873','user1',null)
--insert into orders values('3nnnn',300000,N'112 nguyễn kiệm','02-02-2020','0394087873','user1',null)
--insert into orders values('3oooo',400000,N'112 nguyễn kiệm','01-01-2020','0394087873','user1',null)

--insert into orders values('ODA01',100000,N'112 nguyễn kiệm','12-12-2022','0394087873','user2',null)
--insert into orders values('ODA02',200000,N'112 nguyễn kiệm','11-11-2022','0394087873','user2',null)
--insert into orders values('ODA03',300000,N'112 nguyễn kiệm','10-10-2022','0394087873','user2',null)
--insert into orders values('ODA04',400000,N'112 nguyễn kiệm','09-09-2022','0394087873','user2',null)
--insert into orders values('ODA05',500000,N'112 nguyễn kiệm','08-08-2022','0394087873','user3',null)
--insert into orders values('ODA06',100000,N'112 nguyễn kiệm','07-07-2022','0394087873','user3',null)
--insert into orders values('ODA07',200000,N'112 nguyễn kiệm','06-06-2022','0394087873','user3',null)
--insert into orders values('ODA08',300000,N'112 nguyễn kiệm','05-05-2022','0394087873','user3',null)
--insert into orders values('ODA09',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user4',null)
--insert into orders values('ODA10',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user4',null)
--insert into orders values('ODA11',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user4',null)
--insert into orders values('ODA12',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user4',null)
--insert into orders values('ODA13',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user5',null)
--insert into orders values('ODA14',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user5',null)
--insert into orders values('ODA15',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user5',null)
--insert into orders values('ODA16',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user5',null)
--insert into orders values('ODA17',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user6',null)
--insert into orders values('ODA18',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user6',null)
--insert into orders values('ODA19',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user6',null)
--insert into orders values('ODA20',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user6',null)

--insert into orders values('ODA21',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user2',null)
--insert into orders values('ODA22',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user2',null)
--insert into orders values('ODA23',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user2',null)
--insert into orders values('ODA24',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user2',null)
--insert into orders values('ODA25',400000,N'112 nguyễn kiệm','04-04-2022','0394087873','user3',null)
--insert into orders values('ODA26',500000,N'112 nguyễn kiệm','03-03-2022','0394087873','user3',null)
--insert into orders values('ODA27',300000,N'112 nguyễn kiệm','02-02-2022','0394087873','user3',null)
--insert into orders values('ODA28',400000,N'112 nguyễn kiệm','01-01-2022','0394087873','user3',null)

--select * from orders
--select * from order_status
--select * from order_details
--select * from products

--insert into order_details values(1, 100000, 'ODA01', 1)
--insert into order_details values(2, 200000, 'ODA02', 3)
--insert into order_details values(3, 300000, 'ODA03', 6)
--insert into order_details values(4, 400000, 'ODA04', 3)
--insert into order_details values(5, 500000, 'ODA05', 10)
--insert into order_details values(2, 200000, 'ODA06', 22)
--insert into order_details values(3, 300000, 'ODA07', 12)
--insert into order_details values(4, 400000, 'ODA08', 4)
--insert into order_details values(1, 100000, 'ODA09', 2)
--insert into order_details values(5, 500000, 'ODA10', 8)
--insert into order_details values(2, 200000, 'ODA11', 9)
--insert into order_details values(4, 400000, 'ODA12', 5)
--insert into order_details values(3, 300000, 'ODA13', 2)
--insert into order_details values(1, 100000, 'ODA14', 13)
--insert into order_details values(1, 100000, 'ODA15', 18)
--insert into order_details values(2, 200000, 'ODA16', 20)
--insert into order_details values(3, 300000, 'ODA17', 21)
--insert into order_details values(5, 500000, 'ODA18', 15)
--insert into order_details values(4, 400000, 'ODA19', 14)
--insert into order_details values(2, 200000, 'ODA20', 30)

--insert into order_details values(3, 300000, 'ODA21', 21)
--insert into order_details values(5, 500000, 'ODA22', 15)
--insert into order_details values(4, 400000, 'ODA23', 14)
--insert into order_details values(2, 200000, 'ODA24', 30)
--insert into order_details values(3, 300000, 'ODA25', 21)
--insert into order_details values(5, 500000, 'ODA26', 15)
--insert into order_details values(4, 400000, 'ODA27', 14)
--insert into order_details values(2, 200000, 'ODA28', 30)

--insert into order_status values('aaaaa',4)
--insert into order_status values('bbbbb',4)
--insert into order_status values('ccccc',4)
--insert into order_status values('ddddd',4)
--insert into order_status values('eeeee',4)
--insert into order_status values('fffff',4)
--insert into order_status values('ggggg',4)
--insert into order_status values('hhhhh',4)
--insert into order_status values('iiiii',4)
--insert into order_status values('mmmmm',4)
--insert into order_status values('nnnnn',4)
--insert into order_status values('ooooo',4)

--insert into order_status values('1aaaa',4)
--insert into order_status values('1bbbb',4)
--insert into order_status values('1cccc',4)
--insert into order_status values('1dddd',4)
--insert into order_status values('1eeee',4)
--insert into order_status values('1ffff',4)
--insert into order_status values('1gggg',4)
--insert into order_status values('1hhhh',4)
--insert into order_status values('1iiii',4)
--insert into order_status values('1mmmm',4)
--insert into order_status values('1nnnn',4)
--insert into order_status values('1oooo',4)
--insert into order_status values('2aaaa',4)
--insert into order_status values('2bbbb',4)
--insert into order_status values('2cccc',4)
--insert into order_status values('2dddd',4)
--insert into order_status values('2eeee',4)
--insert into order_status values('2ffff',4)
--insert into order_status values('2gggg',4)
--insert into order_status values('2hhhh',4)
--insert into order_status values('2iiii',4)
--insert into order_status values('2mmmm',4)
--insert into order_status values('2nnnn',4)
--insert into order_status values('2oooo',4)

--insert into order_status values('3aaaa',4)
--insert into order_status values('3bbbb',4)
--insert into order_status values('3cccc',4)
--insert into order_status values('3dddd',4)
--insert into order_status values('3eeee',4)
--insert into order_status values('3ffff',4)
--insert into order_status values('3gggg',4)
--insert into order_status values('3hhhh',4)
--insert into order_status values('3iiii',4)
--insert into order_status values('3mmmm',4)
--insert into order_status values('3nnnn',4)
--insert into order_status values('3oooo',4)

--insert into order_status values('ODA01',1)
--insert into order_status values('ODA02',1)
--insert into order_status values('ODA03',1)
--insert into order_status values('ODA04',2)
--insert into order_status values('ODA05',2)
--insert into order_status values('ODA06',2)
--insert into order_status values('ODA07',2)
--insert into order_status values('ODA08',5)
--insert into order_status values('ODA09',0)
--insert into order_status values('ODA10',4)
--insert into order_status values('ODA11',4)
--insert into order_status values('ODA12',4)
--insert into order_status values('ODA13',4)
--insert into order_status values('ODA14',4)
--insert into order_status values('ODA15',4)
--insert into order_status values('ODA16',4)
--insert into order_status values('ODA17',4)
--insert into order_status values('ODA18',4)
--insert into order_status values('ODA19',4)
--insert into order_status values('ODA20',4)
--insert into order_status values('ODA17',0)
--insert into order_status values('ODA18',1)
--insert into order_status values('ODA19',2)
--insert into order_status values('ODA20',5)

--insert into order_status values('ODA21',0)
--insert into order_status values('ODA22',1)
--insert into order_status values('ODA23',2)
--insert into order_status values('ODA24',5)
--insert into order_status values('ODA25',0)
--insert into order_status values('ODA26',1)
--insert into order_status values('ODA27',2)
--insert into order_status values('ODA28',5)

--  insert into voucher_of_user(username, status, voucher_code, received_date) values
--('user1', 1,'VOUCHER12','2023-12-01' ),
--('user2', 1,'VOUCHER12','2023-12-02' ),
--('user3', 1,'VOUCHER12','2023-12-03' ),
--('user4', 1,'VOUCHER12','2023-12-04' ),
--('user5', 1,'VOUCHER12','2023-12-05' ),
--('user6', 1,'VOUCHER12','2023-12-06' ),
--('user1', 1,'VOUCHER12','2023-12-07' ),
--('user2', 1,'VOUCHER12','2023-12-08' )
--select * from voucher_of_user


insert into blog_posts(title, image, content, publish_date, tag, status, username) values
(N'Trình Tự Để Xem Monogatari Và Dòng Thời Gian Của Phim','https://images.freeimages.com/uploads/11090047/8afc08d5-06db-437f-8084-6a8605a9da6c.jpg',N'Có rất nhiều bộ anime ngoài kia có thể khiến bạn cảm thấy hơi nản lòng khi thử tham gia vì số lượng lớn phải xem – Monogatari rất phù hợp với mô tả đó với 11 bản chuyển thể khác nhau. Biết bắt đầu từ đâu là một vấn đề dễ hiểu ở đây, vì vậy chúng tôi đã chia nhỏ toàn bộ nhượng quyền truyền hình cho bạn để bạn có hướng dẫn rõ ràng về nơi bạn sẽ đến. Vì vậy, Shop Mô Hình Figure sẽ sắp xếp thứ tự xem Monogatari tốt nhất nhé!','2023-10-26','Honor, Romantic, Fantasty',1,'admin1')

insert into blog_posts(title, image, content, publish_date, tag, status, username) values
(N'Most Powerful Devil Fruits In One Piece','https://animemangastore.com/wp-content/uploads/2023/07/Most-Powerful-Devil-Fruits-In-One-Piece-1.jpg.webp',N'in the captivating world of One Piece, where adventurers sail the treacherous seas in search of fame, fortune, and the ultimate treasure, Devil Fruits reign supreme as mysterious and awe-inspiring powers. Among the vast array of abilities showcased throughout the series, some stand out as the epitome of strength and devastation. In this article, we delve into the realm of the most powerful Devil Fruits in One Piece, unlocking the secrets behind their extraordinary might.

From Bartholomew Kuma’s enigmatic Paw-Paw Fruit, capable of deflecting anything at the speed of light, to Charlotte Linlin’s Soul-Soul Fruit, which infuses objects with collected souls, we explore the immense capabilities that these fruits bestow upon their users. Venturing further, we uncover the devastating forces unleashed by Kaido’s Fish-Fish Fruit, Model: Azure Dragon, and Admiral Akainu’s Mag-Mag Fruit, among others.

Prepare to be amazed as we unravel the true potential behind these extraordinary powers, shedding light on all the Devil Fruit abilities that have left fans awestruck. Join us on this thrilling journey through the One Piece universe as we unveil the enigmatic allure of the most powerful Devil Fruits, showcasing their incredible impact on the world and the characters who wield them.','2023-10-26',' One Piece, Adventure, Fantasty',1,'admin1')

--select * from blog_posts