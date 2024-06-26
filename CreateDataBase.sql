CREATE DATABASE [AziendaViaggi]
GO

USE [AziendaViaggi]
GO

CREATE TABLE [AGENZIE_VIAGGIO](
	[Email] [varchar](50) NOT NULL,
	[CodAgenzia] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Sede] [varchar](50) NOT NULL,
	CONSTRAINT [PK_AGENZIE] PRIMARY KEY CLUSTERED 
	(
		[Email] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_AGENZIE] UNIQUE NONCLUSTERED 
	(
		[CodAgenzia] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ALLOGGI](
	[CodAlloggio] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Ind_Citta] [varchar](50) NOT NULL,
	[Ind_Via] [varchar](50) NOT NULL,
	[Ind_NumeroCivico] [varchar](50) NOT NULL,
	[NumeroCamere] [varchar](50) NOT NULL,
	[HOTEL] [varchar](1) NULL,
	[Formula] [varchar](50) NULL,
	[APPARTAMENTO] [varchar](1) NULL,
	CONSTRAINT [PK_ALLOGGI] PRIMARY KEY CLUSTERED 
	(
		[CodAlloggio] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ASSICURAZIONI](
	[CodAssicurazione] [varchar](50) NOT NULL,
	[Tipo] [varchar](50) NOT NULL,
	[Copertura] [varchar](50) NOT NULL,
	[Prezzo] [varchar](50) NOT NULL,
	CONSTRAINT [PK_ASSICURAZIONI] PRIMARY KEY CLUSTERED 
	(
		[CodAssicurazione] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ATTIVITA](
	[CodAttivita] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Descrizione] [varchar](50) NOT NULL,
	[Orario] [varchar](50) NOT NULL,
	[Durata] [varchar](50) NOT NULL,
	CONSTRAINT [PK_ATTIVITA] PRIMARY KEY CLUSTERED 
	(
		[CodAttivita] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[BONIFICI_BANCARI](
	[CodBonifico] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[NomeOrdinante] [varchar](50) NOT NULL,
	[ContoOrdinante] [varchar](50) NOT NULL,
	[NomeBeneficiario] [varchar](50) NOT NULL,
	[ContoBeneficiario] [varchar](50) NOT NULL,
	[Causale] [varchar](50) NOT NULL,
	CONSTRAINT [PK_BONIFICI_BANCARI] PRIMARY KEY CLUSTERED 
	(
		[CodBonifico] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[CARTE_CREDITO](
	[CodCartaCredito] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Intestatario] [varchar](50) NOT NULL,
	[Numero] [varchar](50) NOT NULL,
	[DataScadenza] [varchar](50) NOT NULL,
	[CVV] [varchar](50) NOT NULL,
	CONSTRAINT [PK_CARTE_CREDITO] PRIMARY KEY CLUSTERED 
	(
		[CodCartaCredito] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[CLIENTI](
	[Email] [varchar](50) NOT NULL,
	[CodiceFiscale] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Cognome] [varchar](50) NOT NULL,
	[NumeroTelefono] [varchar](50) NOT NULL,
	CONSTRAINT [PK_CLIENTI] PRIMARY KEY CLUSTERED 
	(
		[Email] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_CLIENTI] UNIQUE NONCLUSTERED 
	(
		[CodiceFiscale] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[DESTINAZIONI](
	[CodDestinazione] [varchar](50) NOT NULL,
	[Paese] [varchar](50) NOT NULL,
	[Citta] [varchar](50) NOT NULL,
	[Descrizione] [varchar](50) NOT NULL,
	CONSTRAINT [PK_DESTINAZIONI] PRIMARY KEY CLUSTERED 
	(
		[CodDestinazione] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[DOCUMENTI_VIAGGIO](
	[NumeroDocumento] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[LuogoRilascio] [varchar](50) NOT NULL,
	[DataScadenza] [varchar](50) NOT NULL,
	[PASSAPORTO] [varchar](1) NOT NULL,
	[CARTA_IDENTITA] [varchar](1) NOT NULL,
	CONSTRAINT [PK_DOCUMENTI_VIAGGIO] PRIMARY KEY CLUSTERED 
	(
		[NumeroDocumento] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[GUIDE_TURISTICHE](
	[CodGuida] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Cognome] [varchar](50) NOT NULL,
	[Lingua] [varchar](50) NOT NULL,
	[Esperienza] [varchar](50) NOT NULL,
	CONSTRAINT [PK_GUIDE_TURISTICHE] PRIMARY KEY CLUSTERED 
	(
		[CodGuida] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ITINERARI](
	[CodPacchetto] [varchar](50) NOT NULL,
	[CodAttivita] [varchar](50) NOT NULL,
	CONSTRAINT [PK_ITINERARI] PRIMARY KEY CLUSTERED 
	(
		[CodAttivita] ASC,
		[CodPacchetto] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[PACCHETTI_TURISTICI](
	[CodPacchetto] [varchar](50) NOT NULL,
	[Nome] [varchar](50) NOT NULL,
	[Descrizione] [varchar](50) NOT NULL,
	[Prezzo] [varchar](50) NOT NULL,
	[CodAgenzia] [varchar](50) NOT NULL,
	[CodGuida] [varchar](50) NULL,
	[CodTrasporto] [varchar](50) NOT NULL,
	[CodAlloggio] [varchar](50) NOT NULL,
	[CodDestinazione] [varchar](50) NOT NULL,
	CONSTRAINT [PK_PACCHETTI_TURISTICI] PRIMARY KEY CLUSTERED 
	(
		[CodPacchetto] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[PRENOTAZIONI](
	[CodPrenotazione] [varchar](50) NOT NULL,
	[DataPrenotazione] [varchar](50) NOT NULL,
	[DataPartenza] [varchar](50) NOT NULL,
	[DataRitorno] [varchar](50) NOT NULL,
	[Importo] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[CodPacchetto] [varchar](50) NOT NULL,
	[CodAssicurazione] [varchar](50) NOT NULL,
	[NumeroDocumento] [varchar](50) NOT NULL,
	[CodCartaCredito] [varchar](50) NULL,
	[CodBonifico] [varchar](50) NULL,
	CONSTRAINT [PK_PRENOTAZIONI] PRIMARY KEY CLUSTERED 
	(
		[CodPrenotazione] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[RECENSIONI](
	[CodPacchetto] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Voto] [varchar](50) NOT NULL,
	[Commento] [varchar](200) NOT NULL,
	CONSTRAINT [PK_RECENSIONI] PRIMARY KEY CLUSTERED 
	(
		[CodPacchetto] ASC,
		[Email] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TRASPORTI](
	[CodTrasporto] [varchar](50) NOT NULL,
	[Compagnia] [varchar](50) NOT NULL,
	[Partenza] [varchar](50) NOT NULL,
	[Destinazione] [varchar](50) NOT NULL,
	[Orario] [varchar](50) NOT NULL,
	[TRAGHETTO] [varchar](1) NULL,
	[AUTOBUS] [varchar](1) NULL,
	[AEREO] [varchar](1) NULL,
	CONSTRAINT [PK_TRASPORTI] PRIMARY KEY CLUSTERED 
	(
		[CodTrasporto] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[BONIFICI_BANCARI]  WITH CHECK ADD  CONSTRAINT [FK_BONIFICI_BANCARI_Email] FOREIGN KEY([Email])
REFERENCES [dbo].[CLIENTI] ([Email])
GO

ALTER TABLE [dbo].[CARTE_CREDITO]  WITH CHECK ADD  CONSTRAINT [FK_CARTE_CREDITO_Email] FOREIGN KEY([Email])
REFERENCES [dbo].[CLIENTI] ([Email])
GO

ALTER TABLE [dbo].[DOCUMENTI_VIAGGIO]  WITH CHECK ADD  CONSTRAINT [FK_DOCUMENTI_VIAGGIO_Email] FOREIGN KEY([Email])
REFERENCES [dbo].[CLIENTI] ([Email])
GO

ALTER TABLE [dbo].[ITINERARI]  WITH CHECK ADD  CONSTRAINT [FK_ITINERARI_CodAttivita] FOREIGN KEY([CodAttivita])
REFERENCES [dbo].[ATTIVITA] ([CodAttivita])
GO

ALTER TABLE [dbo].[ITINERARI]  WITH CHECK ADD  CONSTRAINT [FK_ITINERARI_CodPacchetto] FOREIGN KEY([CodPacchetto])
REFERENCES [dbo].[PACCHETTI_TURISTICI] ([CodPacchetto])
GO

ALTER TABLE [dbo].[PACCHETTI_TURISTICI]  WITH CHECK ADD  CONSTRAINT [FK_PACCHETTI_TURISTICI_CodAgenzia] FOREIGN KEY([CodAgenzia])
REFERENCES [dbo].[AGENZIE_VIAGGIO] ([CodAgenzia])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PACCHETTI_TURISTICI]  WITH CHECK ADD  CONSTRAINT [FK_PACCHETTI_TURISTICI_CodAlloggio] FOREIGN KEY([CodAlloggio])
REFERENCES [dbo].[ALLOGGI] ([CodAlloggio])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PACCHETTI_TURISTICI]  WITH CHECK ADD  CONSTRAINT [FK_PACCHETTI_TURISTICI_CodDestinazione] FOREIGN KEY([CodDestinazione])
REFERENCES [dbo].[DESTINAZIONI] ([CodDestinazione])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PACCHETTI_TURISTICI]  WITH CHECK ADD  CONSTRAINT [FK_PACCHETTI_TURISTICI_CodGuida] FOREIGN KEY([CodGuida])
REFERENCES [dbo].[GUIDE_TURISTICHE] ([CodGuida])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PACCHETTI_TURISTICI]  WITH CHECK ADD  CONSTRAINT [FK_PACCHETTI_TURISTICI_CodTrasporto] FOREIGN KEY([CodTrasporto])
REFERENCES [dbo].[TRASPORTI] ([CodTrasporto])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_CodAssicurazione] FOREIGN KEY([CodAssicurazione])
REFERENCES [dbo].[ASSICURAZIONI] ([CodAssicurazione])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_CodBonifico] FOREIGN KEY([CodBonifico])
REFERENCES [dbo].[BONIFICI_BANCARI] ([CodBonifico])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_CodCartaCredito] FOREIGN KEY([CodCartaCredito])
REFERENCES [dbo].[CARTE_CREDITO] ([CodCartaCredito])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_CodPacchetto] FOREIGN KEY([CodPacchetto])
REFERENCES [dbo].[PACCHETTI_TURISTICI] ([CodPacchetto])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_Email] FOREIGN KEY([Email])
REFERENCES [dbo].[CLIENTI] ([Email])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[PRENOTAZIONI]  WITH CHECK ADD  CONSTRAINT [FK_PRENOTAZIONI_NumeroDocumento] FOREIGN KEY([NumeroDocumento])
REFERENCES [dbo].[DOCUMENTI_VIAGGIO] ([NumeroDocumento])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[RECENSIONI]  WITH CHECK ADD  CONSTRAINT [FK_RECENSIONI_CodPacchetto] FOREIGN KEY([CodPacchetto])
REFERENCES [dbo].[PACCHETTI_TURISTICI] ([CodPacchetto])
GO

ALTER TABLE [dbo].[RECENSIONI]  WITH CHECK ADD  CONSTRAINT [FK_RECENSIONI_Email] FOREIGN KEY([Email])
REFERENCES [dbo].[CLIENTI] ([Email])
GO
