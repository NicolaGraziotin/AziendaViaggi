INSERT INTO AGENZIE_VIAGGIO
	VALUES
	('admin', '00', 'Nicola', 'Misano'),
	('amazon@admin.it', 'AMZ', 'Amazon', 'Milano'),
	('edenviaggi@admin.it', 'EDN', 'Eden Viaggi', 'Roma'),
	('lastminute@admin.it', 'LST', 'Last Minute', 'Torino')
GO

INSERT INTO ALLOGGI
	VALUES
	('HO01', 'Hotel Bella Vista', 'Roma', 'Via Roma', '10', '15', 'S', 'Mezza pensione', 'N'),
	('AP02', 'Appartamento Sole', 'Firenze', 'Via Firenze', '25', '5', 'N', 'Solo pernottamento', 'S'),
	('HO03', 'Hotel Mare', 'Napoli', 'Via Napoli', '8', '20', 'S', 'All inclusive', 'N'),
	('AP04', 'Appartamento Giallo', 'Milano', 'Via Milano', '12', '10', 'N', 'Colazione inclusa', 'S'),
	('HO05', 'Hotel Montagna', 'Torino', 'Via Torino', '15', '30', 'S', 'Solo pernottamento', 'N')
GO

INSERT INTO ASSICURAZIONI
	VALUES
	('AV01', 'Viaggio', 'Assistenza stradale', '50'),
	('AV02', 'Viaggio', 'Annullamento', '100'),
	('AV03', 'Viaggio', 'Bagaglio smarrito', '30'),
	('AV04', 'Viaggio', 'Assicurazione medica', '80'),
	('AV05', 'Viaggio', 'Rimborso spese di viaggio', '120')
GO

INSERT INTO ATTIVITA
	VALUES
	('AT01', 'Escursione sul vulcano', 'Trekking sul vulcano', '09:00', '4'),
	('AT02', 'Tour enogastronomico', 'Visita a cantine e degustazione', '14:00', '3'),
	('AT03', 'Giro in barca', 'Navigazione lungo la costa', '10:30', '5'),
	('AT04', 'Visita guidata alla città', 'Tour storico della città', '11:00', '2'),
	('AT05', 'Attività di snorkeling', 'Esplorazione subacquea', '15:30', '2')
GO

INSERT INTO CLIENTI
	VALUES
	('cliente1@example.com', 'ABCDEF01A23B456C', 'Mario', 'Rossi', '3331234567'),
	('cliente2@example.com', 'GHIJKL12D34E567F', 'Anna', 'Verdi', '3459876543'),
	('cliente3@example.com', 'MNOPQR34F56G789H', 'Luca', 'Bianchi', '3661122334'),
	('cliente4@example.com', 'STUVWX45H67I890L', 'Giulia', 'Neri', '3478765432'),
	('cliente5@example.com', 'YZ1234567890ABCD', 'Paolo', 'Gialli', '3801122334'),
	('nicolagraziotin@gmail.com', 'NCLGRZ', 'Nicola', 'Graziotin', '32722'),
	('nico', '0', '0', '0', '0')
GO

INSERT INTO DESTINAZIONI
	VALUES
	('D01', 'Italia', 'Roma', 'Storia e cultura millenaria.'),
	('D02', 'Francia', 'Parigi', 'Città dell amore e dell arte.'),
	('D03', 'Spagna', 'Barcellona', 'Arte e vita notturna.'),
	('D04', 'Stati Uniti', 'New York', 'La città che non dorme.'),
	('D05', 'Giappone', 'Tokyo', 'Futuristica e tradizionale.')
GO

INSERT INTO GUIDE_TURISTICHE
	VALUES
	('G01', 'Marco', 'Bianchi', 'Italiano', 'Tour storico'),
	('G02', 'Sophie', 'Dubois', 'Francese', 'Tour gastronomico'),
	('G03', 'Juan', 'Martinez', 'Spagnolo', 'Tour culturale'),
	('G04', 'Emily', 'Johnson', 'Inglese', 'Tour in barca'),
	('G05', 'Hiroshi', 'Tanaka', 'Giapponese', 'Tour di shopping')
GO

INSERT INTO TRASPORTI
	VALUES
	('T01', 'Mediterranea Lines', 'Napoli', 'Palermo', '09:00', 'S', 'N', 'N'),
	('T02', 'Eurolines', 'Roma', 'Berlino', '14:30', 'N', 'S', 'N'),
	('T03', 'Air France', 'Parigi', 'New York', '11:45', 'N', 'N', 'S'),
	('T04', 'Italo', 'Milano', 'Firenze', '10:15', 'N', 'S', 'N'),
	('T05', 'Japan Airlines', 'Tokyo', 'Seoul', '13:20', 'N', 'N', 'S')
GO