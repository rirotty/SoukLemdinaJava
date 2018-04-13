-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 11, 2018 at 01:30 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `souklemdina`
--

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `dateCommande` datetime NOT NULL,
  `date_max` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id`, `id_user`, `dateCommande`, `date_max`) VALUES
(1, 3, '2018-02-28 07:52:31', '2018-02-28 07:53:31'),
(2, 3, '2018-02-28 07:53:58', '2018-02-28 07:54:58'),
(3, 3, '2018-02-28 09:28:56', '2018-02-28 09:29:56'),
(4, 3, '2018-02-28 10:08:11', '2018-02-28 10:09:11');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  `texte` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL,
  `id_local` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `nomEvenement` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `nbPlace` int(11) NOT NULL,
  `nbSignal` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `heure` time NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `nbrrating` int(11) DEFAULT NULL,
  `nbruser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `id_profile_follower` int(11) DEFAULT NULL,
  `id_profile_followed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`id`, `id_profile_follower`, `id_profile_followed`) VALUES
(80, 7, 5),
(81, 7, 6),
(83, 6, 7),
(84, 5, 7),
(86, 8, 6),
(111, 6, 5),
(113, 8, 7),
(116, 8, 5),
(138, 5, 8),
(145, 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rue` longtext COLLATE utf8_unicode_ci,
  `zip_code` int(11) DEFAULT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbsignal` int(11) DEFAULT NULL,
  `datenaiss` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `gender`, `firstname`, `lastname`, `rue`, `zip_code`, `ville`, `pays`, `phone`, `nbsignal`, `datenaiss`) VALUES
(3, 'rkad.2107', 'rkad.2107', 'ramy.kader@esprit.tn', 'ramy.kader@esprit.tn', 1, NULL, '$2y$13$ZYJdnIJQLDroD7sY5Ts00.u.Ps7g49pC3DhEDGctIRNfFvKf2Cu1G', '2018-04-10 13:20:44', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'Ramy', 'Kader', 'Jardin Public', 8012, 'Fondouk Djedid', 'TN', '52888256', NULL, '1995-07-21 00:00:00'),
(4, 'Takatsugi', 'takatsugi', 'hatem.abrouz@esprit.tn', 'hatem.abrouz@esprit.tn', 1, NULL, '$2y$13$IwXarAPkQRlxRmlKiCwLDOnvwxpx4eTJODtQgCkjYJJ9As7CeQ8ui', '2018-02-26 09:40:54', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'Hatem', 'Abrouz', 'Les Sapins', 2074, 'Ariana', 'TN', '43061522', NULL, '1994-09-15 00:00:00'),
(5, 'Zeinoub', 'zeinoub', 'zeineb.laabidi1@esprit.tn', 'zeineb.laabidi1@esprit.tn', 1, NULL, '$2y$13$AnoaDpassdgohJ9E41RvjefOUtQOdb6p6Bz7fdSMN.7gIje0MZPZC', '2018-02-20 12:42:42', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Femme', 'Zeineb', 'Laabidi', 'Pôle Technologique El Ghazela', 2083, 'Ariana', 'AF', '94824951', NULL, '1995-01-01 00:00:00'),
(6, 'Safsouf', 'safsouf', 'safa.sahraoui@esprit.tn', 'safa.sahraoui@esprit.tn', 1, NULL, '$2y$13$8XYFDCeZ7ID8e8dZBb2qiOx.eTDa9eNz6i0EdkhqVRhmj65XEC6/C', '2018-03-02 13:57:32', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Femme', 'Safa', 'Sahraoui', 'Cité Les palmerais', 2045, 'Laaouina', 'TN', '26769360', NULL, '1994-09-15 00:00:00'),
(8, 'Omar1', 'omar1', 'omar.ghorbel.1@esprit.tn', 'omar.ghorbel.1@esprit.tn', 1, NULL, '$2y$13$fH5KSg3JbyQNa6Om7WcQreoRNIt3tmfsjtFAOZnwBM23ZUS6xez4e', '2018-02-28 03:18:54', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'Omar', 'Ghorbel', 'Les infirmiers', 6014, 'Mtorrech', 'TN', '23119921', NULL, '1995-07-10 00:00:00'),
(9, 'admin', 'admin', 'rkayx.kader@gmail.com', 'rkayx.kader@gmail.com', 1, NULL, '$2y$13$NGrhiXolJ7elIEwqBEUVXOebP9Z8GrpPvOLMvkI8x4mgE1kPzQWyq', '2018-03-01 18:29:07', 'ky8MTjsnWpRykSxUe9imYPxQ_BbEZTWYWfypUFx29lo', NULL, 'a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}', NULL, 'Administrateur', 'ElSouk', 'No Way', 6666, 'Tunis', 'AF', '66666666', NULL, '1990-07-28 00:00:00'),
(10, 'Rkader2107', 'Rkader2107', 'rkayx.kader@protonmail.com', 'rkayx.kader@protonmail.com', 1, NULL, '$2y$10$FEslZ3W8zu5Zf4xgYgdcguPeVsRBc5v7TsJlrD4AFNp87HX1LZPdy', NULL, NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'Ramy', 'Kader', 'Passage', 1000, 'Tunis', 'Tunisia', '+216 52888256', 0, '1995-07-21 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `jaime`
--

CREATE TABLE `jaime` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ligne_de_commande`
--

CREATE TABLE `ligne_de_commande` (
  `id` int(11) NOT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `id_commande` int(11) DEFAULT NULL,
  `prix_total` double NOT NULL,
  `quntite` int(11) DEFAULT NULL,
  `dateLivraison` date NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `code_postal` int(11) NOT NULL,
  `num_tel` varchar(8) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ligne_de_commande`
--

INSERT INTO `ligne_de_commande` (`id`, `id_produit`, `id_commande`, `prix_total`, `quntite`, `dateLivraison`, `adresse`, `adresse2`, `ville`, `code_postal`, `num_tel`) VALUES
(1, 1, 2, 125, 5, '2018-03-02', 'tunis', NULL, 'hhhhooo', 55564, '55555555'),
(2, 1, 3, 25, 1, '2018-03-02', 'Tunis, Tunisia', NULL, 'Tunis', 55564, '12345678'),
(3, 1, 4, 25, 1, '2018-03-02', 'Tunisia Street, Kuwait City, Kuwait', NULL, 'Kuwait City', 5556422, '55555555');

-- --------------------------------------------------------

--
-- Table structure for table `ligne_wishlist`
--

CREATE TABLE `ligne_wishlist` (
  `id` int(11) NOT NULL,
  `idWishlist` int(11) DEFAULT NULL,
  `idProduit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ligne_wishlist`
--

INSERT INTO `ligne_wishlist` (`id`, `idWishlist`, `idProduit`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_local` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `local`
--

CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbSignal` int(11) DEFAULT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `located` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `local`
--

INSERT INTO `local` (`id`, `id_user`, `superficie`, `prix`, `type`, `description`, `nbSignal`, `adresse`, `telephone`, `titre`, `image`, `located`) VALUES
(28, 10, 200, 30, 'Café', 'Lesm l3ali wel marbet lkhali', NULL, 'Tunisia', '52888256', NULL, '5acd383d7d2db.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_local` int(11) DEFAULT NULL,
  `dateDebutLocation` date NOT NULL,
  `dateFinLocation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 1, 3, 'NewNewNewNewNewNewNewNewNewNewNewNewNewNewNewNew', '2018-02-25 23:23:48'),
(2, 2, 4, 'NewNewNewNewNewNewNewNewNewNewNewNew2.0.1', '2018-02-25 23:25:59'),
(3, 2, 3, 'fuck yopu 1.0.1', '2018-02-25 23:26:12'),
(4, 2, 4, 'Fuck You YOu 2.0.1', '2018-02-25 23:26:35'),
(5, 3, 3, 'ewkhnefwnef', '2018-02-26 03:43:54'),
(6, 4, 4, 'jhrbgvkjbwrekjb wihkjibv kw', '2018-02-26 08:15:58'),
(7, 5, 3, 'iuseuibweuijvkbwojbgf32', '2018-02-26 08:27:30'),
(8, 6, 3, 'sdcvbn mn', '2018-02-26 09:38:06'),
(9, 4, 3, 'Hey Dude', '2018-02-26 10:59:55'),
(10, 7, 3, 'kjbwjekhbjkwejck', '2018-02-27 05:06:17'),
(11, 8, 3, 'hey , salut', '2018-02-28 10:27:08'),
(12, 8, 6, 'jhbewjh', '2018-02-28 10:27:48'),
(13, 9, 6, 'dgxfchvhbj,n.klm', '2018-03-02 14:00:15');

-- --------------------------------------------------------

--
-- Table structure for table `message_metadata`
--

CREATE TABLE `message_metadata` (
  `id` int(11) NOT NULL,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `message_metadata`
--

INSERT INTO `message_metadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 4, 1),
(2, 1, 3, 1),
(3, 2, 3, 1),
(4, 2, 4, 1),
(5, 3, 3, 1),
(6, 3, 4, 1),
(7, 4, 3, 1),
(8, 4, 4, 1),
(9, 5, 4, 1),
(10, 5, 3, 1),
(11, 6, 3, 1),
(12, 6, 4, 1),
(13, 7, 4, 1),
(14, 7, 3, 1),
(15, 8, 4, 1),
(16, 8, 3, 1),
(17, 9, 3, 1),
(18, 9, 4, 1),
(19, 10, 6, 0),
(20, 10, 3, 1),
(21, 11, 6, 1),
(22, 11, 3, 1),
(23, 12, 6, 1),
(24, 12, 3, 1),
(25, 13, 4, 0),
(26, 13, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `notifiable`
--

CREATE TABLE `notifiable` (
  `id` int(11) NOT NULL,
  `identifier` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notifiable`
--

INSERT INTO `notifiable` (`id`, `identifier`, `class`) VALUES
(1, '3', 'SUserBundle\\Entity\\User'),
(2, '4', 'SUserBundle\\Entity\\User'),
(3, '5', 'SUserBundle\\Entity\\User'),
(4, '6', 'SUserBundle\\Entity\\User'),
(5, '8', 'SUserBundle\\Entity\\User'),
(6, '9', 'SUserBundle\\Entity\\User');

-- --------------------------------------------------------

--
-- Table structure for table `notifiable_notification`
--

CREATE TABLE `notifiable_notification` (
  `id` int(11) NOT NULL,
  `notification_id` int(11) DEFAULT NULL,
  `notifiable_entity_id` int(11) DEFAULT NULL,
  `seen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notifiable_notification`
--

INSERT INTO `notifiable_notification` (`id`, `notification_id`, `notifiable_entity_id`, `seen`) VALUES
(1, 1, 1, 0),
(2, 1, 2, 0),
(3, 1, 3, 0),
(4, 1, 4, 0),
(5, 1, 5, 0),
(6, 2, 1, 0),
(7, 2, 2, 0),
(8, 2, 3, 0),
(9, 2, 4, 0),
(10, 2, 5, 0),
(11, 2, 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `date`, `subject`, `message`, `link`) VALUES
(1, '2018-02-28 04:49:12', 'Il y a une promotion de - 19% sur le produit Chaise Mosaique!', NULL, NULL),
(2, '2018-02-28 10:43:21', 'Il y a une promotion de - 33% sur le produit prod modif!', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `participant_events`
--

CREATE TABLE `participant_events` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_evenement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `participant_work`
--

CREATE TABLE `participant_work` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_workshop` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `participant_work`
--

INSERT INTO `participant_work` (`id`, `id_user`, `id_workshop`) VALUES
(1, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `texte` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nb_signal` int(11) DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `id_user`, `date`, `texte`, `titre`, `nb_signal`, `image`, `updated_at`) VALUES
(3, 3, '2018-02-19 00:41:21', 'I think that every kind of art has to be included in this souk. We\'re all craftsmen, we\'re all artists, so let this souk be heared from all over the world, let them know who we are, this is SPARTAAA!! ALLAHOU AKBAAAR ya gued3an, w lak chou 3aguiiid?', 'Every kind of art..', 0, '5a8a0f21c427a.jpg', '2018-02-19 00:41:21'),
(4, 4, '2018-02-19 20:40:50', 'WHAT IT PORTRAYS AND WHERE YOU CAN ADMIRE IT: The Kiss by Rodin is one of the most famous depictions of love in art. Displayed at the Exposition Universelle of 1889, the sculpture was created using Michelangelo’s “non finito” (not finished) technique.', 'THE KISS BY RODIN', 0, '5a8b284261f8f.jpg', '2018-02-19 20:40:50'),
(5, 4, '2018-02-19 20:41:42', 'Final portrait is the new movie directed by Stanley Tucci, who was nominated for an Academy Award for Best Supporting Actor for his performance in “The Lovely Bones” (2009), and is known for his unforgettable performance as “Nigel” in “The Devil wears Pra', 'Final portrait: a movie about Alberto Giacometti', 1, '5a8c05cdb9cec.jpg', '2018-02-20 12:26:05'),
(6, 5, '2018-02-20 00:34:26', 'Mon paradis perdu est cette enfance dans la nature : fabriquer un arc, cueillir des fraises sauvages en été, des champignons en automne, la pêche à l’écrevisse, les balades en forêt, les tempêtes bretonnes sur la plage ou sur la mer émeraude…', 'Quel est votre rapport à la nature, très présente dans votre travail ?', 0, '5a8c04e54357e.jpg', '2018-02-20 12:22:13'),
(7, 5, '2018-02-20 00:41:45', 'Nous avons déniché les meilleures adresses comme http://www.kubera-art-asiatique.fr/galerie/ pour ceux qui cherchent une galerie d\'art asiatique. Nous avons soigneusement vérifié que les collections présentées chez nos sources soient originales.', 'L\'art asiatique et ses galeries', 1, '5a8b60b928588.jpg', '2018-02-20 00:41:45'),
(9, 6, '2018-02-23 03:07:42', 'Les années 80 ont connu un épanchement épouvantable d’expressions musicales que l’on préfère zapper. Les disques qui sortaient n’avaient d’autres choix que d’être oublié ou de rentrer dans l’histoire. A l’heure du jugement dernier, apprenez la définition ', 'Parabellum', 0, '5a8f776eb6d93.png', '2018-02-23 03:07:42'),
(10, 6, '2018-02-23 03:09:55', 'Tweet Cornaqué depuis peu à la promo par le camarade Pat Kebra (ex-guitariste du groupe Oberkampf), The H.O.S.T est un combo originaire de Marseille dont les deux mamelles préférées sont aussi bien le rock que le folk. Formé en 2006, le quatuor a connu un', 'The H.O.S.T “Sound the Charge”', 3, '5a8f77f32b799.jpg', '2018-02-23 03:09:55'),
(15, 3, '2018-04-04 18:04:37', 'I don\'t know but actually, this picture reminds me of the old \"Le Moulin Rouge\" film, it was a piece of art, really, especially for someone with my age back when I watched it.. Not talking about the censored content of course hahahah..', 'Nostalgic \"Moulin\"', NULL, '5ac505a52b36d.jpg', '2018-04-04 18:04:37'),
(16, 3, '2018-04-06 17:43:38', 'De nos jours, plusieurs artisans peuvent s’offrir à vous pour vous convaincre de confier le travail à eux mais il faut bien choisir celui qui s’engage de vous donner un meilleur service.', 'Une intervention fiable..', NULL, '5ac7a3ba2e69c.jpg', '2018-04-06 17:43:38');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quqntite` int(11) DEFAULT NULL,
  `promotion` int(11) NOT NULL,
  `nbSignal` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `updated_at` datetime NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `id_user`, `libelle`, `description`, `quqntite`, `promotion`, `nbSignal`, `type`, `categorie`, `prix`, `updated_at`, `image`) VALUES
(1, 3, 'Chaise Mosaique', 'Chaise Artisanale créée par l\'artiste Senda ben Hamadi', 12, 19, 0, 'Normal', 'Décoration', 25, '2018-02-28 04:43:36', 'b8b7161803b27514072f8925c74f9268,png'),
(2, 3, 'voiture', '4 chev', 10, 16, 0, 'Normal', 'Arts de la table', 10000, '2018-02-28 10:01:41', 'ae46de2d8509a0f505e6d6d3dccbb039,jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `tagline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `about_me` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `id_user`, `tagline`, `image`, `updated_at`, `about_me`) VALUES
(5, 3, '\"But man is not made for defeat. A man can be destroyed but not defeated.\"', '5acd033b1ca8f.jpg', '2018-04-10 19:32:27', '* Studied software-engineering at INSAT, past: Lycée Pilote Bourguiba Tunis, lives in Fondouk El Djedid, Nabul, Tunisia and is from ElKef.'),
(6, 4, 'Nothing to Lose, Nothing to win, No Purpose in this Life', '5a8a1cb612ff3.jpg', '2018-02-19 01:39:18', 'I question everything and worship nothing ! Studied at Iset Rades, now at Esprit, and hopefully nejah mch bch nokeed hakeka nkhalles fi floussi uu, ah w ey, m3a9ed <3'),
(7, 5, 'Strong Like a Tree.', '5a8b53b588049.jpg', '2018-02-19 23:46:13', 'Studies at ESPRIT now, studied at ESPRIT before. Oh yes, and I like to eat :D a lot! No, really, a FUCKING LOT.'),
(8, 6, '\"Qui néglige la musique ignore l\'approche du sublime\"', '5a8cab5a715bd.jpg', '2018-02-21 00:12:26', 'Ami de chacun , ami d\'aucun .. Quand tout est fichu, il y a encore le courage. La meilleure odeur est celle du pain , et le meilleur amour celui des enfants !!'),
(9, 8, 'Nothing To Lose, and He\'s loose', '5a9611e6c8fff.jpg', '2018-02-28 03:20:22', 're9ed akhtawni taw..'),
(10, 10, 'Hey, it\'s a tagline', NULL, '2018-04-10 19:33:37', 'Hi, it\'s an aboutMe section, je suis une princesse, je vous aime putain les fifiiiis d\'amouuree <3 <3');

-- --------------------------------------------------------

--
-- Table structure for table `thread`
--

CREATE TABLE `thread` (
  `id` int(11) NOT NULL,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 3, 'NewNew', '2018-02-25 23:23:48', 0),
(2, 4, 'NewNew2.0.1', '2018-02-25 23:25:59', 0),
(3, 3, 'ewf', '2018-02-26 03:43:54', 0),
(4, 4, 'newSubject', '2018-02-26 08:15:58', 0),
(5, 3, 'HeyDude', '2018-02-26 08:27:30', 0),
(6, 3, 'sdfgbh', '2018-02-26 09:38:06', 0),
(7, 3, 'ewf', '2018-02-27 05:06:17', 0),
(8, 3, 'lknsdblk', '2018-02-28 10:27:08', 0),
(9, 6, 'dxgtzuihkl', '2018-03-02 14:00:15', 0);

-- --------------------------------------------------------

--
-- Table structure for table `thread_metadata`
--

CREATE TABLE `thread_metadata` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thread_metadata`
--

INSERT INTO `thread_metadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 1, 4, 1, NULL, '2018-02-25 23:23:48'),
(2, 1, 3, 0, '2018-02-25 23:23:48', NULL),
(3, 2, 3, 1, '2018-02-25 23:26:12', '2018-02-25 23:26:35'),
(4, 2, 4, 1, '2018-02-25 23:26:35', '2018-02-25 23:26:12'),
(5, 3, 4, 1, NULL, '2018-02-26 03:43:54'),
(6, 3, 3, 0, '2018-02-26 03:43:54', NULL),
(7, 4, 3, 0, '2018-02-26 10:59:55', '2018-02-26 08:15:58'),
(8, 4, 4, 0, '2018-02-26 08:15:58', '2018-02-26 10:59:55'),
(9, 5, 4, 0, NULL, '2018-02-26 08:27:30'),
(10, 5, 3, 0, '2018-02-26 08:27:30', NULL),
(11, 6, 4, 0, NULL, '2018-02-26 09:38:06'),
(12, 6, 3, 0, '2018-02-26 09:38:06', NULL),
(13, 7, 6, 0, NULL, '2018-02-27 05:06:17'),
(14, 7, 3, 0, '2018-02-27 05:06:17', NULL),
(15, 8, 6, 0, '2018-02-28 10:27:48', '2018-02-28 10:27:08'),
(16, 8, 3, 0, '2018-02-28 10:27:08', '2018-02-28 10:27:48'),
(17, 9, 4, 0, NULL, '2018-03-02 14:00:15'),
(18, 9, 6, 0, '2018-03-02 14:00:15', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `idUser`) VALUES
(1, 3),
(2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist_produit`
--

CREATE TABLE `wishlist_produit` (
  `wishlist_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `workshop`
--

CREATE TABLE `workshop` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `nomWorkshop` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `nbPlace` int(11) NOT NULL,
  `prix` double NOT NULL,
  `nbSignal` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `video` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `planning` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `workshop`
--

INSERT INTO `workshop` (`id`, `id_user`, `nomWorkshop`, `adresse`, `type`, `dateDebut`, `dateFin`, `nbPlace`, `prix`, `nbSignal`, `description`, `video`, `planning`, `image`, `updated_at`) VALUES
(1, 3, 'Martouba', 'Tunis Tunis Tunis Lemdina Tunis', 'patisserie Traditionnelle', '2018-06-10 00:00:00', '2019-06-10 00:00:00', 20, 6400, NULL, 'Martouba Magdouda yaateha douda', NULL, NULL, '5a9647892f890.jpg', '2018-02-28 07:09:13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_6EEAA67D6B3CA4B` (`id_user`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9474526C6B3CA4B` (`id_user`),
  ADD KEY `IDX_9474526CD1AA708F` (`id_post`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_67F068BC6553C9D8` (`id_local`),
  ADD KEY `IDX_67F068BC6B3CA4B` (`id_user`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B26681E6B3CA4B` (`id_user`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_68344470A3363D26` (`id_profile_follower`),
  ADD KEY `IDX_6834447057E28877` (`id_profile_followed`);

--
-- Indexes for table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`);

--
-- Indexes for table `jaime`
--
ALTER TABLE `jaime`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_3CB778056B3CA4B` (`id_user`),
  ADD KEY `IDX_3CB77805D1AA708F` (`id_post`);

--
-- Indexes for table `ligne_de_commande`
--
ALTER TABLE `ligne_de_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7982ACE6F7384557` (`id_produit`),
  ADD KEY `IDX_7982ACE63E314AE8` (`id_commande`);

--
-- Indexes for table `ligne_wishlist`
--
ALTER TABLE `ligne_wishlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_C37B3B07CF42006A` (`idWishlist`),
  ADD KEY `IDX_C37B3B07391C87D5` (`idProduit`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_49CA4E7D6B3CA4B` (`id_user`),
  ADD KEY `IDX_49CA4E7D6553C9D8` (`id_local`);

--
-- Indexes for table `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_8BD688E86B3CA4B` (`id_user`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5E9E89CB6B3CA4B` (`id_user`),
  ADD KEY `IDX_5E9E89CB6553C9D8` (`id_local`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B6BD307FE2904019` (`thread_id`),
  ADD KEY `IDX_B6BD307FF624B39D` (`sender_id`);

--
-- Indexes for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4632F005537A1329` (`message_id`),
  ADD KEY `IDX_4632F0059D1C3019` (`participant_id`);

--
-- Indexes for table `notifiable`
--
ALTER TABLE `notifiable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_ADCFE0FAEF1A9D84` (`notification_id`),
  ADD KEY `IDX_ADCFE0FAC3A0A4F8` (`notifiable_entity_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `participant_events`
--
ALTER TABLE `participant_events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_E1FBE9CB6B3CA4B` (`id_user`),
  ADD KEY `IDX_E1FBE9CB8B13D439` (`id_evenement`);

--
-- Indexes for table `participant_work`
--
ALTER TABLE `participant_work`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_87FA7ABE6B3CA4B` (`id_user`),
  ADD KEY `IDX_87FA7ABECBB4EE51` (`id_workshop`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5A8A6C8D6B3CA4B` (`id_user`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_29A5EC276B3CA4B` (`id_user`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8157AA0F6B3CA4B` (`id_user`);

--
-- Indexes for table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_31204C83B03A8386` (`created_by_id`);

--
-- Indexes for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_40A577C8E2904019` (`thread_id`),
  ADD KEY `IDX_40A577C89D1C3019` (`participant_id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_9CE12A31FE6E88D7` (`idUser`);

--
-- Indexes for table `wishlist_produit`
--
ALTER TABLE `wishlist_produit`
  ADD PRIMARY KEY (`wishlist_id`,`produit_id`),
  ADD KEY `IDX_B6A93A5DFB8E54CD` (`wishlist_id`),
  ADD KEY `IDX_B6A93A5DF347EFB` (`produit_id`);

--
-- Indexes for table `workshop`
--
ALTER TABLE `workshop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9B6F02C46B3CA4B` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT for table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `jaime`
--
ALTER TABLE `jaime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ligne_de_commande`
--
ALTER TABLE `ligne_de_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ligne_wishlist`
--
ALTER TABLE `ligne_wishlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `local`
--
ALTER TABLE `local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `message_metadata`
--
ALTER TABLE `message_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `notifiable`
--
ALTER TABLE `notifiable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `participant_events`
--
ALTER TABLE `participant_events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `participant_work`
--
ALTER TABLE `participant_work`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `thread`
--
ALTER TABLE `thread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `workshop`
--
ALTER TABLE `workshop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67D6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526C6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_9474526CD1AA708F` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681E6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `FK_6834447057E28877` FOREIGN KEY (`id_profile_followed`) REFERENCES `profile` (`id`),
  ADD CONSTRAINT `FK_68344470A3363D26` FOREIGN KEY (`id_profile_follower`) REFERENCES `profile` (`id`);

--
-- Constraints for table `jaime`
--
ALTER TABLE `jaime`
  ADD CONSTRAINT `FK_3CB778056B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_3CB77805D1AA708F` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`);

--
-- Constraints for table `ligne_de_commande`
--
ALTER TABLE `ligne_de_commande`
  ADD CONSTRAINT `FK_7982ACE63E314AE8` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `FK_7982ACE6F7384557` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Constraints for table `ligne_wishlist`
--
ALTER TABLE `ligne_wishlist`
  ADD CONSTRAINT `FK_C37B3B07391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FK_C37B3B07CF42006A` FOREIGN KEY (`idWishlist`) REFERENCES `wishlist` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  ADD CONSTRAINT `FK_ADCFE0FAC3A0A4F8` FOREIGN KEY (`notifiable_entity_id`) REFERENCES `notifiable` (`id`),
  ADD CONSTRAINT `FK_ADCFE0FAEF1A9D84` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`);

--
-- Constraints for table `participant_events`
--
ALTER TABLE `participant_events`
  ADD CONSTRAINT `FK_E1FBE9CB6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_E1FBE9CB8B13D439` FOREIGN KEY (`id_evenement`) REFERENCES `evenement` (`id`);

--
-- Constraints for table `participant_work`
--
ALTER TABLE `participant_work`
  ADD CONSTRAINT `FK_87FA7ABE6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_87FA7ABECBB4EE51` FOREIGN KEY (`id_workshop`) REFERENCES `workshop` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_5A8A6C8D6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC276B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `FK_8157AA0F6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FK_9CE12A31FE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `fos_user` (`id`);

--
-- Constraints for table `wishlist_produit`
--
ALTER TABLE `wishlist_produit`
  ADD CONSTRAINT `FK_B6A93A5DF347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_B6A93A5DFB8E54CD` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `workshop`
--
ALTER TABLE `workshop`
  ADD CONSTRAINT `FK_9B6F02C46B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
