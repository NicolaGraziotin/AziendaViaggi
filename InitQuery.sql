INSERT INTO AGENZIE_VIAGGIO
	VALUES
	('admin', 'AG000', 'Nicola', 'Misano'),
	('amazon@admin.it', 'AG001', 'Amazon', 'Milano'),
	('edenviaggi@admin.it', 'AG002', 'Eden Viaggi', 'Roma'),
	('lastminute@admin.it', 'AG003', 'Last Minute', 'Torino')
GO

INSERT INTO ALLOGGI
	VALUES
	('HO001', 'Hotel Bella Vista', 'Roma', 'Via Roma', '10', '15', 'S', 'Mezza pensione', 'N'),
	('AP002', 'Appartamento Sole', 'Firenze', 'Via Firenze', '25', '5', 'N', 'Solo pernottamento', 'S'),
	('HO003', 'Hotel Mare', 'Napoli', 'Via Napoli', '8', '20', 'S', 'All inclusive', 'N'),
	('AP004', 'Appartamento Giallo', 'Milano', 'Via Milano', '12', '10', 'N', 'Colazione inclusa', 'S'),
	('HO005', 'Hotel Montagna', 'Torino', 'Via Torino', '15', '30', 'S', 'Solo pernottamento', 'N')
GO

INSERT INTO ASSICURAZIONI
	VALUES
	('AV001', 'Viaggio', 'Assistenza stradale', '50'),
	('AV002', 'Viaggio', 'Annullamento', '100'),
	('AV003', 'Viaggio', 'Bagaglio smarrito', '30'),
	('AV004', 'Viaggio', 'Assicurazione medica', '80'),
	('AV005', 'Viaggio', 'Rimborso spese di viaggio', '120')
GO

INSERT INTO ATTIVITA
	VALUES
	('AT001', 'Escursione sul vulcano', 'Trekking sul vulcano', '09:00', '4'),
	('AT002', 'Tour enogastronomico', 'Visita a cantine e degustazione', '14:00', '3'),
	('AT003', 'Giro in barca', 'Navigazione lungo la costa', '10:30', '5'),
	('AT004', 'Visita guidata alla città', 'Tour storico della città', '11:00', '2'),
	('AT005', 'Attività di snorkeling', 'Esplorazione subacquea', '15:30', '2')
GO

INSERT INTO CLIENTI
	VALUES
	('cliente1@example.com', 'ABCDEF01A23B456C', 'Mario', 'Rossi', '3331234567'),
	('cliente2@example.com', 'GHIJKL12D34E567F', 'Anna', 'Verdi', '3459876543'),
	('cliente3@example.com', 'MNOPQR34F56G789H', 'Luca', 'Bianchi', '3661122334'),
	('cliente4@example.com', 'STUVWX45H67I890L', 'Giulia', 'Neri', '3478765432'),
	('cliente5@example.com', 'YZ1234567890ABCD', 'Paolo', 'Gialli', '3801122334'),
	('nicolagraziotin@gmail.com', 'GRZNCL', 'Nicola', 'Graziotin', '32722'),
	('nico', '0', '0', '0', '0')
GO

INSERT INTO DESTINAZIONI
	VALUES
	('DS001', 'Italia', 'Roma', 'Storia e cultura millenaria.'),
	('DS002', 'Francia', 'Parigi', 'Città dell amore e dell arte.'),
	('DS003', 'Spagna', 'Barcellona', 'Arte e vita notturna.'),
	('DS004', 'Stati Uniti', 'New York', 'La città che non dorme.'),
	('DS005', 'Giappone', 'Tokyo', 'Futuristica e tradizionale.')
GO

INSERT INTO GUIDE_TURISTICHE
	VALUES
	('GT001', 'Marco', 'Bianchi', 'Italiano', 'Tour storico'),
	('GT002', 'Sophie', 'Dubois', 'Francese', 'Tour gastronomico'),
	('GT003', 'Juan', 'Martinez', 'Spagnolo', 'Tour culturale'),
	('GT004', 'Emily', 'Johnson', 'Inglese', 'Tour in barca'),
	('GT005', 'Hiroshi', 'Tanaka', 'Giapponese', 'Tour di shopping')
GO

INSERT INTO TRASPORTI
	VALUES
	('TR001', 'Mediterranea Lines', 'Napoli', 'Palermo', '09:00', 'S', 'N', 'N'),
	('TR002', 'Eurolines', 'Roma', 'Berlino', '14:30', 'N', 'S', 'N'),
	('TR003', 'Air France', 'Parigi', 'New York', '11:45', 'N', 'N', 'S'),
	('TR004', 'Italo', 'Milano', 'Firenze', '10:15', 'N', 'S', 'N'),
	('TR005', 'Japan Airlines', 'Tokyo', 'Seoul', '13:20', 'N', 'N', 'S')
GO