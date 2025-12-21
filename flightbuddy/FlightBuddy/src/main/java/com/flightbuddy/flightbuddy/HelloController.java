package com.flightbuddy.flightbuddy;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Pane mapPane;

    @FXML
    public void initialize() {

        // 1) Wczytanie mapy
        Image map = new Image(getClass().getResourceAsStream("/Map_Europe.png"));
        ImageView mapView = new ImageView(map);
        mapView.fitWidthProperty().bind(mapPane.widthProperty());
        mapView.setPreserveRatio(true);

        mapPane.setOnMouseClicked(e -> {
            System.out.println((int)e.getX() + ", " + (int)e.getY() + ",");
        });

        mapPane.getChildren().add(mapView);

        // Polygon POLSKA
        Polygon poland = new Polygon(
                430, 314,
                426, 326,
                432, 335,
                435, 357,
                447, 363,
                452, 368,
                452, 364,
                459, 368,
                471, 380,
                484, 382,
                510, 385,
                521, 368,
                520, 341,
                516, 334,
                522, 328,
                520, 306,
                512, 302,
                481, 301,
                474, 304,
                469, 296,
                456, 296,
                428, 309
        );

        poland.setFill(Color.TRANSPARENT);  // niewidoczne
        poland.setStroke(Color.TRANSPARENT);

        // Efekt podświetlenia
        poland.setOnMouseEntered(e ->
                poland.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        poland.setOnMouseExited(e ->
                poland.setFill(Color.TRANSPARENT));

        // Dodanie polygonu nad mapą
        mapPane.getChildren().add(poland);

        // otworzenie mapy polski
        poland.setOnMouseClicked(e -> openCountryMap("Map_Poland.png"));


        // Polygon DANIA
        Polygon denmark = new Polygon(
                373, 291,
                372, 283,
                368, 283,
                367, 276,
                372, 275,
                368, 274,
                368, 265,
                371, 257,
                380, 256,
                390, 248,
                389, 260,
                389, 268,
                388, 277,
                382, 278,
                391, 284,
                398, 276,
                407, 270,
                412, 281,
                407, 290,
                400, 298,
                392, 296,
                388, 290,
                387, 286,
                380, 287,
                382, 293,
                373, 293
        );

        denmark.setFill(Color.TRANSPARENT);  // niewidoczne
        denmark.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        denmark.setOnMouseEntered(e ->
                denmark.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        denmark.setOnMouseExited(e ->
                denmark.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(denmark);

        // Polygon lithuania
        Polygon lithuania = new Polygon(
                509, 300,
                511, 290,
                496, 286,
                495, 271,
                505, 267,
                513, 270,
                526, 270,
                532, 267,
                547, 280,
                548, 288,
                540, 292,
                539, 303,
                529, 309,
                520, 311,
                511, 300

        );

        lithuania.setFill(Color.TRANSPARENT);  // niewidoczne
        lithuania.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        lithuania.setOnMouseEntered(e ->
                lithuania.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        lithuania.setOnMouseExited(e ->
                lithuania.setFill(Color.TRANSPARENT));


        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(lithuania);


        // Polygon russia
        Polygon russia = new Polygon(
                562, 194,
                597, 151,
                584, 136,
                588, 128,
                582, 121,
                585, 118,
                580, 113,
                582, 110,
                583, 101,
                585, 100,
                575, 76,
                584, 62,
                577, 52,
                569, 45,
                571, 38,
                576, 35,
                588, 21,
                599, 20,
                636, 30,
                680, 54,
                694, 72,
                668, 92,
                605, 74,
                615, 89,
                630, 94,
                628, 106,
                632, 124,
                642, 129,
                658, 136,
                661, 126,
                648, 118,
                650, 110,
                661, 119,
                677, 123,
                684, 118,
                680, 104,
                689, 96,
                703, 84,
                713, 93,
                720, 94,
                724, 81,
                719, 72,
                723, 57,
                716, 44,
                742, 48,
                747, 57,
                735, 61,
                738, 75,
                748, 81,
                756, 75,
                761, 63,
                767, 62,
                776, 55,
                794, 45,
                807, 40,
                821, 33,
                817, 53,
                832, 44,
                847, 43,
                857, 36,
                865, 45,
                876, 36,
                869, 25,
                859, 20,
                854, 5,
                897, 23,
                899, 300,
                883, 306,
                884, 317,
                880, 328,
                873, 334,
                888, 346,
                870, 352,
                859, 350,
                841, 348,
                820, 352,
                808, 344,
                796, 339,
                781, 340,
                762, 356,
                761, 367,
                752, 360,
                744, 374,
                746, 384,
                742, 390,
                747, 403,
                758, 404,
                767, 420,
                760, 432,
                750, 436,
                744, 451,
                752, 469,
                761, 495,
                752, 501,
                744, 496,
                736, 490,
                732, 485,
                724, 480,
                715, 481,
                708, 474,
                694, 474,
                680, 470,
                670, 462,
                654, 450,
                654, 444,
                660, 430,
                664, 420,
                674, 415,
                668, 408,
                668, 403,
                681, 404,
                682, 385,
                683, 378,
                667, 370,
                662, 370,
                656, 362,
                645, 364,
                635, 364,
                635, 356,
                624, 348,
                624, 337,
                616, 332,
                602, 336,
                596, 324,
                605, 321,
                611, 312,
                603, 307,
                592, 295,
                591, 287,
                591, 281,
                576, 278,
                574, 274,
                560, 265,
                560, 245,
                558, 220,
                566, 211,
                584, 207,
                567, 189,
                562, 194

        );

        russia.setFill(Color.TRANSPARENT);  // niewidoczne


        // Polygon kaliningrad
        Polygon kaliningrad = new Polygon(
                480, 299,
                510, 301,
                509, 296,
                509, 289,
                495, 284,
                493, 292,
                484, 292,
                481, 301

        );
        kaliningrad.setFill(Color.TRANSPARENT);

        //ROSJA + KALININGRAD jako calosc
        Polygon[] russiaGroup = { russia, kaliningrad };

        EventHandler<MouseEvent> enterRussia = e -> {
            for (Polygon p : russiaGroup)
                p.setFill(Color.rgb(255,255,255,0.3));
        };

        EventHandler<MouseEvent> exitRussia = e -> {
            for (Polygon p : russiaGroup)
                p.setFill(Color.TRANSPARENT);
        };

        russia.setOnMouseEntered(enterRussia);
        russia.setOnMouseExited(exitRussia);

        kaliningrad.setOnMouseEntered(enterRussia);
        kaliningrad.setOnMouseExited(exitRussia);

        mapPane.getChildren().addAll(russia, kaliningrad);

    // Polygon belgium
        Polygon belarus = new Polygon(
                519, 311,
                521, 325,
                516, 333,
                520, 345,
                524, 343,
                528, 340,
                541, 341,
                563, 345,
                582, 346,
                588, 348,
                588, 342,
                595, 336,
                598, 337,
                595, 324,
                604, 320,
                609, 315,
                601, 309,
                601, 306,
                592, 300,
                591, 294,
                591, 285,
                591, 279,
                584, 276,
                577, 280,
                575, 274,
                568, 271,
                565, 272,
                551, 280,
                547, 283,
                548, 288,
                542, 289,
                538, 300,
                530, 307,
                520, 311,
                522, 324,
                516, 332,
                521, 346


        );

        belarus.setFill(Color.TRANSPARENT);  // niewidoczne
        belarus.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        belarus.setOnMouseEntered(e ->
                belarus.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        belarus.setOnMouseExited(e ->
                belarus.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(belarus);

        // Polygon belgium
        Polygon finland = new Polygon(
                489, 35,
                514, 48,
                518, 67,
                522, 82,
                522, 99,
                536, 110,
                533, 118,
                527, 116,
                514, 138,
                506, 140,
                496, 148,
                499, 167,
                496, 191,
                505, 200,
                516, 208,
                530, 203,
                543, 201,
                558, 197,
                599, 151,
                583, 139,
                587, 126,
                582, 122,
                585, 120,
                580, 114,
                583, 113,
                582, 105,
                584, 101,
                576, 74,
                584, 63,
                578, 52,
                572, 48,
                572, 42,
                571, 38,
                571, 32,
                576, 31,
                576, 31,
                576, 24,
                560, 12,
                544, 18,
                541, 36,
                536, 37,
                535, 42,
                523, 38,
                512, 40,
                504, 32,
                500, 29,
                494, 32,
                490, 32,
                487, 36,
                497, 42,
                507, 44

        );

        finland.setFill(Color.TRANSPARENT);  // niewidoczne
        finland.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        finland.setOnMouseEntered(e ->
                finland.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        finland.setOnMouseExited(e ->
                finland.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(finland);

        // Polygon belgium
        Polygon ukraine = new Polygon(
                520, 345,
                524, 360,
                522, 364,
                511, 385,
                508, 387,
                505, 398,
                511, 400,
                526, 403,
                532, 406,
                542, 403,
                551, 398,
                559, 395,
                572, 403,
                576, 403,
                575, 410,
                578, 418,
                583, 428,
                574, 428,
                570, 434,
                564, 442,
                579, 440,
                587, 436,
                592, 424,
                602, 424,
                604, 430,
                614, 432,
                620, 432,
                610, 437,
                611, 442,
                618, 445,
                618, 459,
                624, 458,
                627, 448,
                632, 450,
                636, 447,
                644, 448,
                650, 448,
                650, 441,
                644, 441,
                637, 444,
                634, 443,
                632, 436,
                629, 434,
                629, 429,
                639, 424,
                648, 422,
                656, 416,
                661, 416,
                664, 411,
                667, 404,
                678, 402,
                679, 393,
                681, 386,
                680, 377,
                663, 372,
                656, 364,
                647, 366,
                643, 364,
                633, 363,
                632, 352,
                624, 348,
                624, 340,
                619, 332,
                608, 333,
                599, 338,
                594, 337,
                588, 348,
                581, 347,
                575, 347,
                566, 346,
                548, 340,
                526, 340,
                521, 347


        );

        ukraine.setFill(Color.TRANSPARENT);  // niewidoczne
        ukraine.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        ukraine.setOnMouseEntered(e ->
                ukraine.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        ukraine.setOnMouseExited(e ->
                ukraine.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(ukraine);

        // Polygon belgium
        Polygon moldova = new Polygon(
                555, 400,
                564, 418,
                564, 439,
                572, 433,
                572, 424,
                580, 426,
                584, 425,
                579, 418,
                578, 412,
                575, 409,
                576, 402,
                572, 401,
                561, 394,
                553, 396,
                553, 400


        );

        moldova.setFill(Color.TRANSPARENT);  // niewidoczne
        moldova.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        moldova.setOnMouseEntered(e ->
                moldova.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        moldova.setOnMouseExited(e ->
                moldova.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(moldova);

        // Polygon belgium
        Polygon estonia = new Polygon(
                564, 215,
                560, 222,
                559, 240,
                556, 248,
                547, 248,
                544, 244,
                526, 240,
                522, 236,
                504, 235,
                504, 234,
                508, 227,
                505, 225,
                510, 224,
                515, 221,
                519, 220,
                523, 215,
                535, 215,
                544, 212,
                561, 215,
                558, 226,
                555, 249


        );

        estonia.setFill(Color.TRANSPARENT);  // niewidoczne
        estonia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        estonia.setOnMouseEntered(e ->
                estonia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        estonia.setOnMouseExited(e ->
                estonia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(estonia);

        // Polygon belgium
        Polygon latvia = new Polygon(
                548, 279,
                564, 272,
                563, 264,
                560, 255,
                555, 248,
                545, 249,
                542, 242,
                528, 244,
                528, 254,
                520, 257,
                515, 255,
                508, 244,
                499, 250,
                496, 258,
                493, 262,
                495, 272,
                504, 267,
                524, 271,
                530, 266,
                548, 281

        );

        latvia.setFill(Color.TRANSPARENT);  // niewidoczne
        latvia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        latvia.setOnMouseEntered(e ->
                latvia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        latvia.setOnMouseExited(e ->
                latvia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(latvia);

        // Polygon belgium
        Polygon sweden = new Polygon(
                397, 225,
                403, 221,
                403, 209,
                409, 203,
                408, 188,
                412, 182,
                408, 173,
                406, 156,
                408, 140,
                413, 131,
                426, 130,
                428, 124,
                424, 120,
                432, 90,
                439, 90,
                448, 75,
                448, 65,
                456, 52,
                463, 54,
                466, 44,
                478, 44,
                486, 44,
                488, 35,
                505, 42,
                512, 48,
                516, 60,
                520, 72,
                521, 94,
                520, 99,
                509, 97,
                499, 108,
                496, 112,
                496, 119,
                498, 123,
                498, 127,
                492, 134,
                484, 138,
                477, 142,
                468, 151,
                465, 159,
                460, 166,
                460, 179,
                456, 186,
                460, 201,
                470, 212,
                460, 213,
                455, 214,
                448, 218,
                455, 221,
                452, 232,
                451, 232,
                446, 258,
                440, 272,
                432, 272,
                424, 276,
                426, 282,
                416, 284,
                413, 270,
                414, 263,
                409, 258,
                404, 240,
                399, 235,
                398, 225
        );

        sweden.setFill(Color.TRANSPARENT);  // niewidoczne
        sweden.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        sweden.setOnMouseEntered(e ->
                sweden.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        sweden.setOnMouseExited(e ->
                sweden.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(sweden);

        // Polygon belgium
        Polygon norway = new Polygon(
                578, 30,
                591, 19,
                575, 11,
                593, 7,
                584, 0,
                543, 0,
                521, 1,
                502, 10,
                488, 15,
                482, 22,
                480, 15,
                470, 14,
                464, 22,
                453, 32,
                444, 31,
                435, 37,
                428, 40,
                437, 44,
                450, 48,
                452, 48,
                447, 52,
                441, 52,
                436, 56,
                433, 66,
                428, 73,
                421, 80,
                414, 91,
                415, 100,
                410, 106,
                412, 116,
                405, 114,
                403, 120,
                400, 123,
                393, 126,
                385, 136,
                385, 138,
                387, 140,
                379, 140,
                372, 142,
                369, 148,
                357, 152,
                359, 157,
                352, 164,
                340, 168,
                336, 175,
                337, 186,
                337, 196,
                337, 204,
                341, 219,
                344, 232,
                352, 240,
                360, 240,
                367, 236,
                376, 232,
                383, 224,
                388, 223,
                390, 213,
                394, 216,
                396, 224,
                401, 224,
                403, 217,
                404, 206,
                408, 206,
                408, 192,
                414, 187,
                409, 176,
                407, 154,
                408, 136,
                416, 131,
                424, 131,
                429, 124,
                422, 118,
                432, 91,
                448, 72,
                448, 67,
                456, 51,
                463, 53,
                468, 43,
                478, 43,
                487, 42,
                488, 32,
                495, 29,
                499, 27,
                507, 40,
                527, 41,
                532, 39,
                538, 38,
                546, 17,
                559, 12,
                570, 16,
                574, 23,
                573, 32
        );

        norway.setFill(Color.TRANSPARENT);  // niewidoczne
        norway.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        norway.setOnMouseEntered(e ->
                norway.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        norway.setOnMouseExited(e ->
                norway.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(norway);

        // Polygon belgium
        Polygon germany = new Polygon(
                427, 313,
                424, 324,
                431, 333,
                431, 344,
                433, 357,
                428, 356,
                412, 366,
                406, 365,
                405, 372,
                409, 380,
                421, 392,
                420, 396,
                416, 398,
                412, 398,
                413, 412,
                408, 412,
                402, 409,
                400, 411,
                393, 412,
                391, 408,
                386, 416,
                383, 415,
                380, 414,
                379, 412,
                368, 408,
                368, 411,
                360, 411,
                360, 400,
                366, 389,
                355, 386,
                346, 379,
                344, 374,
                343, 372,
                346, 368,
                345, 361,
                341, 360,
                345, 356,
                345, 341,
                351, 340,
                354, 334,
                356, 324,
                356, 318,
                359, 312,
                363, 312,
                365, 316,
                367, 316,
                368, 312,
                373, 309,
                376, 311,
                377, 309,
                375, 304,
                372, 304,
                372, 300,
                372, 295,
                372, 292,
                377, 293,
                384, 294,
                388, 300,
                388, 304,
                390, 306,
                401, 306,
                402, 306,
                405, 305,
                409, 300,
                416, 301,
                419, 301,
                422, 304,
                424, 308,
                428, 311,
                428, 312
        );

        germany.setFill(Color.TRANSPARENT);  // niewidoczne
        germany.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        germany.setOnMouseEntered(e ->
                germany.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        germany.setOnMouseExited(e ->
                germany.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(germany);

        // Polygon belgium
        Polygon czechia = new Polygon(
                470, 380,
                469, 372,
                464, 372,
                461, 367,
                453, 366,
                453, 369,
                448, 365,
                448, 363,
                437, 359,
                433, 357,
                428, 355,
                410, 365,
                407, 364,
                404, 372,
                411, 383,
                417, 388,
                423, 395,
                428, 395,
                433, 392,
                433, 386,
                442, 386,
                444, 392,
                455, 392,
                456, 392,
                462, 391,
                464, 387,
                470, 380
        );

        czechia.setFill(Color.TRANSPARENT);  // niewidoczne
        czechia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        czechia.setOnMouseEntered(e ->
                czechia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        czechia.setOnMouseExited(e ->
                czechia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(czechia);

        // Polygon belgium
        Polygon slovakia = new Polygon(
                454, 394,
                453, 405,
                456, 403,
                464, 408,
                472, 406,
                472, 404,
                476, 400,
                480, 400,
                484, 400,
                490, 397,
                497, 394,
                503, 398,
                504, 396,
                505, 392,
                506, 388,
                506, 384,
                497, 380,
                486, 381,
                483, 383,
                476, 378,
                476, 379,
                469, 380,
                464, 385,
                460, 392,
                455, 391,
                452, 400

                );

        slovakia.setFill(Color.TRANSPARENT);  // niewidoczne
        slovakia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        slovakia.setOnMouseEntered(e ->
                slovakia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        slovakia.setOnMouseExited(e ->
                slovakia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(slovakia);

        // Polygon belgium
        Polygon austria = new Polygon(
                384, 420,
                387, 415,
                391, 409,
                395, 412,
                402, 408,
                411, 410,
                414, 409,
                412, 404,
                412, 397,
                414, 396,
                416, 394,
                419, 392,
                423, 392,
                428, 395,
                430, 389,
                435, 386,
                439, 388,
                441, 391,
                450, 392,
                453, 392,
                453, 395,
                452, 400,
                454, 405,
                453, 406,
                448, 410,
                447, 412,
                447, 413,
                448, 421,
                445, 424,
                438, 423,
                435, 423,
                432, 428,
                425, 428,
                420, 426,
                419, 425,
                412, 426,
                408, 424,
                404, 420,
                401, 417,
                396, 417,
                392, 420,
                396, 424,
                390, 424,
                388, 424,
                385, 424,
                383, 423,
                380, 422,
                380, 416

        );

        austria.setFill(Color.TRANSPARENT);  // niewidoczne
        austria.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        austria.setOnMouseEntered(e ->
                austria.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        austria.setOnMouseExited(e ->
                austria.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(austria);

        // Polygon belgium
        Polygon switzerland = new Polygon(
                388, 424,
                381, 420,
                380, 416,
                380, 412,
                369, 406,
                365, 409,
                355, 411,
                352, 414,
                348, 422,
                344, 428,
                341, 428,
                348, 430,
                352, 435,
                354, 440,
                360, 435,
                363, 436,
                365, 430,
                366, 425,
                368, 432,
                371, 433,
                377, 429,
                380, 430,
                383, 430,
                384, 425,
                386, 424

        );

        switzerland.setFill(Color.TRANSPARENT);  // niewidoczne
        switzerland.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        switzerland.setOnMouseEntered(e ->
                switzerland.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        switzerland.setOnMouseExited(e ->
                switzerland.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(switzerland);

        // Polygon belgium
        Polygon netherlands = new Polygon(
                357, 323,
                352, 340,
                346, 342,
                343, 352,
                340, 353,
                336, 351,
                334, 350,
                334, 350,
                330, 349,
                323, 348,
                317, 349,
                317, 349,
                320, 344,
                324, 342,
                326, 340,
                328, 333,
                329, 326,
                331, 324,
                334, 325,
                334, 325,
                335, 325,
                336, 321,
                337, 319,
                340, 319,
                344, 319,
                348, 318,
                353, 318,
                356, 319,
                357, 321
                );

        netherlands.setFill(Color.TRANSPARENT);  // niewidoczne
        netherlands.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        netherlands.setOnMouseEntered(e ->
                netherlands.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        netherlands.setOnMouseExited(e ->
                netherlands.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(netherlands);

        // Polygon belgium
        Polygon belgium = new Polygon(
                306, 356,
                316, 349,
                321, 352,
                327, 350,
                327, 350,
                330, 349,
                332, 349,
                336, 350,
                340, 351,
                341, 352,
                344, 354,
                346, 354,
                344, 365,
                344, 372,
                343, 372,
                341, 380,
                338, 381,
                336, 381,
                336, 380,
                332, 375,
                332, 372,
                332, 372,
                332, 372,
                326, 373,
                323, 373,
                324, 373,
                324, 370,
                323, 367,
                319, 366,
                315, 364,
                310, 363,
                307, 360,
                305, 358,
                305, 356

                );

        belgium.setFill(Color.TRANSPARENT);  // niewidoczne
        belgium.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        belgium.setOnMouseEntered(e ->
                belgium.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        belgium.setOnMouseExited(e ->
                belgium.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(belgium);

        // Polygon hungary
        Polygon luxemburg = new Polygon(
                340, 372,
                347, 376,
                347, 383,
                339, 382,
                340, 377,
                340, 373

        );

        luxemburg.setFill(Color.TRANSPARENT);  // niewidoczne
        luxemburg.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        luxemburg.setOnMouseEntered(e ->
                luxemburg.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        luxemburg.setOnMouseExited(e ->
                luxemburg.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(luxemburg);

        // Polygon bosnia
        Polygon hungary = new Polygon(
                456, 403,
                462, 406,
                470, 408,
                471, 403,
                476, 401,
                483, 397,
                485, 399,
                495, 393,
                498, 395,
                504, 396,
                507, 400,
                509, 403,
                510, 404,
                504, 408,
                502, 413,
                499, 420,
                497, 425,
                493, 432,
                487, 434,
                485, 436,
                484, 433,
                480, 432,
                476, 432,
                472, 435,
                468, 439,
                464, 439,
                460, 437,
                456, 435,
                455, 431,
                450, 427,
                449, 425,
                445, 424,
                445, 423,
                448, 419,
                449, 416,
                448, 412,
                448, 410,
                452, 408,
                453, 408,
                455, 406,
                455, 405

        );

        hungary.setFill(Color.TRANSPARENT);  // niewidoczne
        hungary.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        hungary.setOnMouseEntered(e ->
                hungary.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        hungary.setOnMouseExited(e ->
                hungary.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(hungary);

        // Polygon bosnia
        Polygon slovenia = new Polygon(
                422, 429,
                420, 434,
                420, 440,
                423, 440,
                423, 440,
                419, 444,
                419, 445,
                419, 448,
                421, 450,
                424, 450,
                424, 447,
                427, 442,
                429, 446,
                433, 442,
                436, 440,
                437, 440,
                440, 435,
                440, 432,
                441, 428,
                446, 428,
                446, 424,
                439, 423,
                437, 424,
                430, 426,
                422, 430,
                422, 430,
                419, 434,
                417, 436


                );

        slovenia.setFill(Color.TRANSPARENT);  // niewidoczne
        slovenia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        slovenia.setOnMouseEntered(e ->
                slovenia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        slovenia.setOnMouseExited(e ->
                slovenia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(slovenia);

        // Polygon bosnia
        Polygon croatia = new Polygon(
                477, 451,
                476, 444,
                472, 442,
                472, 439,
                471, 436,
                464, 436,
                460, 439,
                456, 435,
                451, 431,
                449, 427,
                443, 429,
                438, 432,
                440, 437,
                435, 440,
                432, 442,
                428, 444,
                424, 444,
                419, 447,
                420, 449,
                421, 449,
                424, 449,
                424, 448,
                427, 445,
                428, 448,
                432, 452,
                433, 456,
                436, 460,
                440, 466,
                441, 469,
                447, 473,
                448, 475,
                455, 476,
                459, 479,
                461, 484,
                459, 478,
                454, 475,
                452, 471,
                450, 468,
                447, 464,
                443, 460,
                440, 456,
                440, 452,
                440, 449,
                440, 444,
                447, 449,
                448, 449,
                450, 449,
                452, 448,
                457, 449,
                460, 452,
                464, 452,
                465, 452,
                468, 452,
                475, 453,
                475, 454
        );

        croatia.setFill(Color.TRANSPARENT);  // niewidoczne
        croatia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        croatia.setOnMouseEntered(e ->
                croatia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        croatia.setOnMouseExited(e ->
                croatia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(croatia);

        // Polygon bosnia
        Polygon bosnia = new Polygon(
                463, 480,
                468, 480,
                471, 476,
                471, 472,
                477, 471,
                477, 466,
                478, 459,
                472, 458,
                472, 457,
                476, 454,
                474, 450,
                472, 451,
                467, 450,
                462, 450,
                458, 450,
                454, 449,
                452, 448,
                449, 447,
                446, 447,
                443, 446,
                440, 444,
                440, 449,
                440, 455,
                443, 459,
                448, 464,
                451, 469,
                456, 477,
                460, 481,
                464, 484,
                468, 481
        );

        bosnia.setFill(Color.TRANSPARENT);  // niewidoczne
        bosnia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        bosnia.setOnMouseEntered(e ->
                bosnia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        bosnia.setOnMouseExited(e ->
                bosnia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(bosnia);

        // Polygon iceland
        Polygon montenegro = new Polygon(
                464, 488,
                468, 483,
                468, 482,
                470, 476,
                471, 474,
                472, 468,
                478, 472,
                478, 475,
                480, 478,
                482, 480,
                484, 486,
                478, 486,
                474, 488,
                474, 492,
                474, 493,
                472, 494,
                470, 489,
                468, 487,
                465, 486,
                464, 485,
                463, 485
        );

        montenegro.setFill(Color.TRANSPARENT);  // niewidoczne
        montenegro.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        montenegro.setOnMouseEntered(e ->
                montenegro.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        montenegro.setOnMouseExited(e ->
                montenegro.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(montenegro);

        // Polygon iceland
        Polygon kosovo = new Polygon(
                483, 487,
                483, 484,
                486, 479,
                490, 473,
                494, 476,
                500, 484,
                500, 489,
                497, 492,
                492, 492,
                490, 492,
                488, 491,
                483, 486
        );

        kosovo.setFill(Color.TRANSPARENT);  // niewidoczne
        kosovo.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        kosovo.setOnMouseEntered(e ->
                kosovo.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        kosovo.setOnMouseExited(e ->
                kosovo.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(kosovo);

        // Polygon iceland
        Polygon serbia = new Polygon(
                502, 489,
                505, 491,
                507, 488,
                508, 484,
                508, 478,
                512, 476,
                508, 472,
                506, 469,
                506, 464,
                509, 461,
                509, 459,
                504, 457,
                503, 456,
                500, 455,
                499, 454,
                495, 452,
                498, 449,
                496, 444,
                493, 444,
                490, 441,
                490, 439,
                485, 436,
                481, 434,
                476, 432,
                470, 433,
                469, 438,
                472, 440,
                476, 450,
                475, 456,
                472, 457,
                476, 457,
                478, 463,
                477, 470,
                476, 472,
                484, 477,
                488, 474,
                493, 477,
                496, 484,
                497, 488,
                498, 491,
                503, 492,
                505, 491
        );

        serbia.setFill(Color.TRANSPARENT);  // niewidoczne
        serbia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        serbia.setOnMouseEntered(e ->
                serbia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        serbia.setOnMouseExited(e ->
                serbia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(serbia);


        // Polygon iceland
        Polygon albania = new Polygon(
                475, 496,
                475, 495,
                477, 493,
                477, 490,
                477, 487,
                478, 486,
                482, 488,
                484, 492,
                487, 492,
                488, 492,
                492, 497,
                488, 498,
                486, 504,
                486, 508,
                488, 513,
                489, 517,
                489, 524,
                488, 524,
                484, 528,
                484, 528,
                482, 527,
                482, 524,
                477, 520,
                476, 520,
                476, 518,
                476, 515,
                476, 512,
                477, 510,
                478, 508,
                479, 506,
                480, 500,
                478, 497,
                475, 496,
                476, 496
        );

        albania.setFill(Color.TRANSPARENT);  // niewidoczne
        albania.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        albania.setOnMouseEntered(e ->
                albania.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        albania.setOnMouseExited(e ->
                albania.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(albania);

        // Polygon iceland
        Polygon macedonia = new Polygon(
                491, 512,
                488, 508,
                488, 503,
                491, 498,
                488, 492,
                493, 494,
                496, 492,
                503, 490,
                508, 492,
                512, 496,
                514, 504,
                507, 507,
                503, 508,
                500, 511,
                493, 512,
                492, 512
        );

        macedonia.setFill(Color.TRANSPARENT);  // niewidoczne
        macedonia.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        macedonia.setOnMouseEntered(e ->
                macedonia.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        macedonia.setOnMouseExited(e ->
                macedonia.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(macedonia);

        // Polygon iceland
        Polygon romania = new Polygon(
                580, 440,
                581, 447,
                577, 452,
                572, 452,
                569, 453,
                570, 459,
                570, 464,
                569, 467,
                564, 466,
                561, 464,
                559, 464,
                555, 462,
                550, 460,
                547, 461,
                541, 467,
                539, 468,
                533, 468,
                532, 466,
                531, 466,
                524, 466,
                519, 467,
                512, 466,
                510, 466,
                507, 467,
                507, 468,
                508, 461,
                509, 461,
                510, 459,
                508, 456,
                504, 456,
                500, 455,
                496, 455,
                497, 452,
                497, 450,
                497, 444,
                493, 442,
                490, 440,
                489, 439,
                486, 436,
                489, 434,
                493, 433,
                496, 430,
                496, 426,
                500, 420,
                501, 415,
                503, 410,
                506, 408,
                511, 408,
                511, 403,
                516, 402,
                521, 404,
                524, 404,
                528, 404,
                532, 406,
                537, 404,
                544, 401,
                548, 400,
                551, 400,
                552, 403,
                555, 408,
                557, 410,
                560, 414,
                565, 420,
                564, 426,
                564, 432,
                564, 438,
                564, 441,
                566, 443,
                571, 444,
                575, 443,
                576, 440,
                579, 440

                );

        romania.setFill(Color.TRANSPARENT);  // niewidoczne
        romania.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        romania.setOnMouseEntered(e ->
                romania.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        romania.setOnMouseExited(e ->
                romania.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(romania);

        // Polygon iceland
        Polygon bulgaria = new Polygon(
                569, 467,
                562, 466,
                558, 461,
                550, 460,
                542, 464,
                539, 466,
                533, 467,
                529, 467,
                526, 469,
                519, 469,
                517, 469,
                511, 465,
                509, 465,
                506, 466,
                509, 473,
                514, 476,
                514, 479,
                508, 484,
                508, 489,
                502, 492,
                507, 492,
                510, 497,
                512, 500,
                512, 503,
                516, 504,
                521, 504,
                524, 501,
                525, 500,
                530, 500,
                534, 505,
                536, 507,
                542, 504,
                543, 500,
                544, 496,
                552, 496,
                555, 495,
                561, 495,
                562, 495,
                559, 492,
                556, 490,
                558, 486,
                560, 483,
                561, 478,
                564, 473,
                570, 470,
                570, 468

        );

        bulgaria.setFill(Color.TRANSPARENT);  // niewidoczne
        bulgaria.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        bulgaria.setOnMouseEntered(e ->
                bulgaria.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        bulgaria.setOnMouseExited(e ->
                bulgaria.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(bulgaria);

        // Polygon iceland
        Polygon greece = new Polygon(
                484, 528,
                488, 522,
                491, 517,
                492, 513,
                497, 511,
                502, 508,
                503, 507,
                507, 507,
                512, 507,
                512, 504,
                516, 503,
                518, 504,
                524, 502,
                525, 501,
                531, 503,
                533, 506,
                539, 505,
                541, 503,
                545, 498,
                548, 497,
                552, 497,
                552, 502,
                547, 509,
                546, 512,
                543, 512,
                540, 512,
                535, 512,
                532, 510,
                526, 510,
                522, 512,
                522, 514,
                523, 520,
                524, 522,
                522, 522,
                517, 522,
                512, 522,
                512, 518,
                513, 517,
                513, 515,
                510, 516,
                507, 516,
                507, 522,
                508, 524,
                510, 527,
                514, 532,
                512, 535,
                511, 539,
                510, 544,
                512, 547,
                518, 547,
                516, 544,
                515, 543,
                515, 541,
                515, 539,
                517, 541,
                518, 544,
                523, 545,
                526, 546,
                524, 550,
                522, 550,
                521, 555,
                521, 559,
                521, 562,
                520, 562,
                515, 562,
                512, 562,
                512, 562,
                512, 567,
                514, 575,
                514, 575,
                514, 575,
                508, 573,
                508, 573,
                503, 573,
                501, 573,
                498, 569,
                498, 565,
                498, 561,
                496, 556,
                496, 554,
                496, 549,
                495, 546,
                490, 545,
                490, 541,
                488, 537,
                484, 536,
                481, 532,
                483, 528,
                487, 524,
                487, 524,
                490, 520,
                492, 516,
                492, 512,
                496, 512,
                496, 512,
                499, 511,
                501, 509,
                504, 507,
                508, 507,
                510, 507,
                512, 506,
                515, 503,
                517, 503,
                524, 500,
                525, 500,
                527, 503,
                533, 506,
                535, 506,
                542, 505,
                544, 503,
                548, 497,
                549, 496

        );

        greece.setFill(Color.TRANSPARENT);  // niewidoczne
        greece.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        greece.setOnMouseEntered(e ->
                greece.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        greece.setOnMouseExited(e ->
                greece.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(greece);

        // Polygon iceland
        Polygon turkey = new Polygon(
                543, 514,
                544, 509,
                549, 504,
                552, 498,
                552, 492,
                559, 494,
                561, 494,
                564, 499,
                566, 502,
                570, 504,
                576, 505,
                581, 508,
                583, 508,
                591, 508,
                595, 507,
                595, 507,
                600, 503,
                608, 499,
                612, 496,
                617, 494,
                627, 493,
                629, 493,
                636, 498,
                644, 503,
                648, 504,
                655, 507,
                668, 512,
                670, 508,
                676, 508,
                683, 507,
                692, 505,
                694, 503,
                701, 501,
                702, 500,
                709, 500,
                712, 506,
                713, 509,
                715, 520,
                720, 524,
                726, 525,
                724, 528,
                720, 532,
                720, 535,
                719, 543,
                719, 544,
                719, 547,
                721, 549,
                725, 558,
                726, 562,
                722, 567,
                719, 568,
                718, 567,
                716, 562,
                712, 564,
                709, 564,
                704, 564,
                698, 562,
                694, 566,
                686, 568,
                679, 571,
                674, 573,
                668, 572,
                664, 572,
                659, 568,
                658, 568,
                641, 571,
                645, 576,
                645, 579,
                639, 580,
                638, 576,
                640, 573,
                640, 570,
                636, 572,
                627, 573,
                624, 573,
                618, 576,
                617, 578,
                609, 582,
                606, 582,
                600, 577,
                597, 572,
                593, 570,
                585, 570,
                587, 576,
                583, 579,
                580, 579,
                576, 579,
                570, 576,
                569, 573,
                569, 571,
                567, 571,
                562, 571,
                558, 572,
                555, 572,
                553, 568,
                555, 567,
                556, 565,
                556, 564,
                555, 560,
                555, 558,
                553, 552,
                548, 550,
                542, 547,
                542, 545,
                548, 550,
                549, 551,
                549, 546,
                549, 537,
                549, 537,
                549, 532,
                547, 531,
                544, 526,
                544, 524,
                549, 520,
                550, 520,
                552, 520,
                554, 520,
                562, 521,
                569, 521,
                572, 520,
                573, 516,
                580, 512,
                572, 508,
                565, 508,
                560, 508,
                558, 511,
                554, 514,
                549, 515,
                544, 515,
                544, 514,
                544, 511,
                544, 511,
                545, 508,
                549, 504,
                549, 504,
                550, 498,
                552, 494

        );

        turkey.setFill(Color.TRANSPARENT);  // niewidoczne
        turkey.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        turkey.setOnMouseEntered(e ->
                turkey.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        turkey.setOnMouseExited(e ->
                turkey.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(turkey);

        // Polygon iceland
        Polygon spain = new Polygon(
                195, 492,
                192, 487,
                188, 484,
                188, 480,
                194, 477,
                198, 474,
                201, 472,
                205, 469,
                208, 472,
                210, 474,
                216, 474,
                218, 473,
                222, 473,
                224, 473,
                231, 474,
                236, 476,
                243, 476,
                246, 474,
                252, 475,
                258, 476,
                263, 476,
                267, 476,
                269, 480,
                272, 483,
                276, 487,
                281, 487,
                284, 486,
                286, 484,
                289, 484,
                292, 484,
                292, 484,
                297, 486,
                301, 490,
                304, 492,
                308, 492,
                313, 492,
                312, 499,
                309, 503,
                308, 503,
                304, 503,
                303, 505,
                303, 506,
                297, 507,
                293, 508,
                292, 512,
                290, 513,
                289, 514,
                286, 520,
                286, 520,
                284, 525,
                281, 528,
                281, 530,
                280, 532,
                279, 535,
                282, 541,
                282, 544,
                281, 550,
                276, 550,
                276, 550,
                276, 550,
                276, 557,
                271, 562,
                268, 563,
                264, 568,
                258, 573,
                253, 575,
                248, 576,
                240, 576,
                237, 576,
                234, 575,
                230, 576,
                226, 582,
                220, 579,
                216, 579,
                218, 574,
                216, 570,
                214, 568,
                209, 566,
                208, 565,
                208, 559,
                210, 554,
                206, 547,
                207, 546,
                212, 540,
                209, 536,
                207, 529,
                209, 530,
                211, 527,
                212, 524,
                213, 517,
                213, 509,
                216, 503,
                215, 499,
                211, 496,
                204, 496,
                196, 496,
                196, 493,
                192, 485,
                188, 481,
                191, 476,
                196, 474

        );

        spain.setFill(Color.TRANSPARENT);  // niewidoczne
        spain.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        spain.setOnMouseEntered(e ->
                spain.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        spain.setOnMouseExited(e ->
                spain.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(spain);

        // Polygon iceland
        Polygon portugal = new Polygon(
                207, 568,
                207, 564,
                208, 559,
                210, 556,
                208, 551,
                204, 548,
                205, 545,
                211, 541,
                209, 536,
                206, 532,
                208, 528,
                211, 528,
                212, 520,
                212, 515,
                212, 507,
                215, 504,
                216, 499,
                211, 496,
                203, 495,
                198, 492,
                196, 492,
                192, 492,
                194, 495,
                194, 500,
                192, 508,
                192, 510,
                190, 514,
                195, 520,
                195, 525,
                193, 531,
                188, 536,
                186, 539,
                184, 541,
                187, 548,
                187, 548,
                192, 549,
                192, 560,
                192, 565,
                192, 567,
                196, 570,
                202, 568,
                203, 568,
                206, 564

        );

        portugal.setFill(Color.TRANSPARENT);  // niewidoczne
        portugal.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        portugal.setOnMouseEntered(e ->
                portugal.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        portugal.setOnMouseExited(e ->
                portugal.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(portugal);

        // Polygon iceland
        Polygon ireland = new Polygon(
                215, 308,
                216, 313,
                215, 318,
                219, 324,
                216, 327,
                216, 329,
                213, 336,
                209, 338,
                207, 338,
                204, 340,
                200, 340,
                196, 344,
                196, 344,
                190, 347,
                187, 350,
                183, 350,
                184, 344,
                186, 344,
                180, 344,
                178, 344,
                177, 344,
                174, 342,
                177, 340,
                176, 339,
                173, 336,
                178, 336,
                180, 336,
                180, 335,
                181, 334,
                184, 333,
                184, 332,
                181, 329,
                181, 329,
                181, 329,
                183, 328,
                184, 328,
                184, 324,
                188, 322,
                188, 319,
                184, 317,
                181, 320,
                179, 320,
                179, 320,
                176, 316,
                180, 315,
                181, 312,
                179, 310,
                179, 307,
                178, 303,
                180, 302,
                184, 303,
                187, 305,
                189, 306,
                192, 306,
                192, 306,
                194, 306,
                196, 305,
                196, 299,
                192, 292,
                192, 291,
                194, 289,
                198, 288,
                200, 288,
                204, 288,
                204, 291,
                204, 294,
                203, 296,
                200, 296,
                200, 299,
                200, 304,
                200, 305,
                201, 307,
                205, 307,
                207, 306,
                207, 304,
                208, 302,
                212, 302,
                212, 305,
                212, 306,
                218, 306

        );

        ireland.setFill(Color.TRANSPARENT);  // niewidoczne
        ireland.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        ireland.setOnMouseEntered(e ->
                ireland.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        ireland.setOnMouseExited(e ->
                ireland.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(ireland);

        // Polygon iceland
        Polygon italy = new Polygon(
                357, 465,
                353, 463,
                351, 461,
                351, 456,
                351, 454,
                348, 448,
                348, 446,
                352, 446,
                354, 448,
                356, 445,
                352, 443,
                351, 439,
                351, 439,
                355, 436,
                355, 436,
                358, 436,
                360, 434,
                362, 432,
                364, 428,
                365, 425,
                367, 428,
                368, 432,
                368, 434,
                372, 433,
                374, 431,
                376, 431,
                382, 430,
                382, 428,
                384, 427,
                386, 427,
                389, 426,
                392, 424,
                395, 422,
                398, 421,
                401, 420,
                404, 420,
                407, 423,
                411, 425,
                415, 425,
                418, 425,
                424, 425,
                426, 428,
                423, 430,
                419, 430,
                418, 430,
                417, 432,
                417, 436,
                416, 437,
                412, 439,
                409, 440,
                407, 440,
                406, 440,
                404, 442,
                403, 444,
                403, 444,
                408, 448,
                406, 449,
                406, 449,
                407, 453,
                406, 457,
                406, 460,
                411, 464,
                411, 466,
                412, 469,
                416, 471,
                418, 474,
                418, 476,
                421, 482,
                422, 484,
                427, 492,
                429, 495,
                431, 496,
                435, 498,
                436, 498,
                438, 498,
                443, 497,
                444, 497,
                444, 497,
                442, 502,
                441, 504,
                441, 504,
                450, 509,
                452, 511,
                463, 517,
                464, 519,
                465, 521,
                465, 521,
                465, 524,
                465, 526,
                461, 525,
                458, 523,
                455, 519,
                450, 518,
                449, 518,
                447, 525,
                447, 527,
                447, 529,
                451, 532,
                453, 536,
                453, 537,
                453, 541,
                451, 544,
                449, 547,
                448, 551,
                444, 555,
                443, 556,
                440, 556,
                440, 556,
                440, 557,
                440, 560,
                437, 565,
                435, 571,
                434, 576,
                432, 576,
                426, 575,
                426, 571,
                426, 569,
                423, 565,
                412, 562,
                409, 562,
                405, 554,
                407, 551,
                411, 550,
                415, 550,
                418, 552,
                418, 554,
                427, 554,
                430, 553,
                432, 552,
                434, 552,
                436, 552,
                443, 549,
                443, 549,
                443, 540,
                442, 535,
                440, 532,
                440, 527,
                438, 525,
                433, 524,
                432, 520,
                432, 517,
                432, 516,
                426, 513,
                423, 512,
                420, 506,
                420, 504,
                415, 504,
                412, 504,
                403, 504,
                402, 499,
                400, 494,
                396, 488,
                391, 482,
                385, 476,
                385, 475,
                385, 469,
                380, 463,
                376, 460,
                373, 460,
                371, 460,
                365, 460,
                364, 464,
                364, 465,
                364, 466,
                358, 468

        );

        italy.setFill(Color.TRANSPARENT);  // niewidoczne
        italy.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        italy.setOnMouseEntered(e ->
                italy.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        italy.setOnMouseExited(e ->
                italy.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(italy);

        // Polygon iceland
        Polygon france = new Polygon(
                262, 474,
                263, 476,
                268, 480,
                269, 485,
                273, 488,
                278, 488,
                285, 486,
                288, 484,
                291, 484,
                300, 488,
                304, 493,
                308, 494,
                313, 494,
                312, 492,
                311, 485,
                311, 481,
                315, 476,
                320, 471,
                324, 471,
                331, 474,
                336, 476,
                347, 478,
                357, 476,
                357, 470,
                357, 467,
                356, 464,
                352, 463,
                350, 457,
                347, 452,
                352, 445,
                352, 445,
                352, 441,
                352, 438,
                350, 434,
                348, 428,
                347, 428,
                346, 429,
                344, 432,
                339, 432,
                341, 427,
                344, 425,
                346, 420,
                351, 418,
                352, 415,
                356, 409,
                356, 409,
                358, 399,
                360, 392,
                362, 388,
                362, 384,
                356, 383,
                349, 381,
                344, 377,
                338, 375,
                333, 378,
                330, 375,
                326, 371,
                316, 366,
                312, 362,
                308, 360,
                304, 357,
                300, 356,
                299, 359,
                296, 363,
                292, 368,
                292, 372,
                285, 376,
                283, 378,
                280, 385,
                274, 387,
                269, 386,
                267, 384,
                267, 376,
                265, 375,
                262, 375,
                260, 374,
                260, 375,
                260, 377,
                264, 381,
                265, 386,
                266, 392,
                264, 395,
                257, 394,
                256, 394,
                256, 394,
                248, 393,
                244, 392,
                244, 391,
                236, 391,
                234, 393,
                232, 396,
                232, 404,
                233, 412,
                239, 412,
                241, 411,
                247, 411,
                251, 412,
                256, 413,
                256, 417,
                256, 422,
                259, 425,
                262, 427,
                266, 432,
                270, 436,
                270, 440,
                270, 453,
                269, 457,
                269, 457,
                266, 471,
                264, 475,
                264, 476,
                267, 477,
                273, 482,
                280, 487,
                286, 487,
                293, 487,
                300, 487,
                307, 488,
                312, 489

        );

        france.setFill(Color.TRANSPARENT);  // niewidoczne
        france.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        france.setOnMouseEntered(e ->
                france.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        france.setOnMouseExited(e ->
                france.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(france);

        // Polygon iceland
        Polygon britain = new Polygon(
                224, 372,
                228, 373,
                231, 372,
                234, 368,
                235, 368,
                241, 368,
                244, 368,
                249, 361,
                255, 360,
                261, 363,
                266, 363,
                276, 360,
                280, 360,
                291, 360,
                293, 360,
                292, 355,
                292, 352,
                288, 351,
                287, 349,
                288, 345,
                291, 343,
                295, 341,
                296, 333,
                296, 328,
                296, 325,
                288, 328,
                282, 328,
                280, 328,
                279, 326,
                281, 322,
                281, 320,
                278, 318,
                275, 314,
                278, 310,
                278, 308,
                273, 302,
                270, 298,
                267, 297,
                264, 294,
                264, 291,
                263, 285,
                261, 281,
                258, 278,
                256, 276,
                252, 275,
                252, 273,
                251, 269,
                250, 267,
                252, 264,
                253, 261,
                256, 255,
                261, 249,
                261, 249,
                258, 247,
                249, 247,
                244, 248,
                240, 250,
                240, 247,
                241, 243,
                242, 239,
                249, 235,
                249, 232,
                249, 232,
                249, 228,
                245, 229,
                239, 232,
                235, 232,
                233, 232,
                228, 232,
                226, 232,
                227, 238,
                227, 238,
                228, 240,
                223, 247,
                218, 250,
                220, 254,
                220, 255,
                219, 262,
                219, 268,
                220, 270,
                220, 277,
                221, 281,
                224, 282,
                228, 284,
                231, 283,
                232, 289,
                227, 294,
                224, 296,
                229, 298,
                237, 296,
                244, 294,
                249, 292,
                248, 297,
                247, 301,
                247, 306,
                249, 317,
                249, 318,
                247, 319,
                243, 319,
                240, 319,
                236, 319,
                236, 319,
                234, 319,
                234, 319,
                234, 320,
                234, 321,
                234, 324,
                233, 324,
                232, 327,
                233, 328,
                235, 328,
                236, 329,
                236, 329,
                236, 336,
                234, 344,
                231, 344,
                227, 344,
                227, 347,
                229, 348,
                236, 348,
                244, 349,
                248, 351,
                249, 351,
                250, 351,
                250, 351,
                244, 353,
                240, 354,
                233, 363,
                233, 364,
                231, 367,
                226, 372,
                226, 372,
                228, 372,
                230, 368,
                232, 368

        );

        britain.setFill(Color.TRANSPARENT);  // niewidoczne
        britain.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        britain.setOnMouseEntered(e ->
                britain.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        britain.setOnMouseExited(e ->
                britain.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(britain);

        // Polygon iceland
        Polygon iceland = new Polygon(
                4, 82,
                8, 82,
                16, 86,
                20, 89,
                21, 92,
                22, 99,
                22, 104,
                24, 105,
                27, 104,
                28, 102,
                32, 100,
                32, 96,
                32, 89,
                33, 87,
                38, 96,
                40, 97,
                42, 94,
                42, 88,
                43, 88,
                49, 90,
                53, 94,
                54, 96,
                56, 93,
                56, 92,
                56, 88,
                61, 91,
                62, 92,
                65, 91,
                67, 90,
                74, 86,
                75, 84,
                75, 80,
                77, 80,
                80, 84,
                83, 88,
                84, 88,
                86, 88,
                88, 85,
                89, 84,
                90, 88,
                88, 92,
                89, 92,
                94, 93,
                95, 98,
                97, 99,
                100, 100,
                104, 103,
                104, 104,
                104, 110,
                104, 110,
                103, 113,
                100, 118,
                96, 123,
                92, 125,
                87, 127,
                84, 128,
                76, 133,
                74, 135,
                74, 136,
                67, 140,
                64, 140,
                59, 141,
                57, 145,
                49, 146,
                33, 142,
                32, 142,
                32, 138,
                25, 136,
                18, 137,
                10, 136,
                6, 133,
                8, 130,
                12, 128,
                16, 128,
                17, 128,
                17, 128,
                16, 127,
                16, 121,
                13, 119,
                5, 117,
                0, 116,
                5, 115,
                8, 110,
                14, 108,
                16, 108,
                14, 104,
                8, 102,
                2, 100,
                1, 98,
                0, 90,
                0, 88,
                1, 88,
                5, 88,
                5, 88,
                5, 88,
                5, 84,
                4, 82,
                7, 82

        );

        iceland.setFill(Color.TRANSPARENT);  // niewidoczne
        iceland.setStroke(Color.TRANSPARENT);

        // 3) Efekt podświetlenia
        iceland.setOnMouseEntered(e ->
                iceland.setFill(Color.color(1, 1, 1, 0.3))); // półprzezroczysta poświata

        iceland.setOnMouseExited(e ->
                iceland.setFill(Color.TRANSPARENT));

        // 4) Dodanie polygonu nad mapą
        mapPane.getChildren().add(iceland);
    }

    private void openCountryMap(String fileName)
    {
        Image image = new Image(getClass().getResourceAsStream("/" + fileName));
        ImageView imageView = new ImageView(image);

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(900);
        imageView.setFitHeight(600);

        Pane root = new Pane(imageView);
        Scene scene = new Scene(root, 900, 600);

        Stage stage = new Stage();
        stage.setTitle(fileName);
        stage.setScene(scene);
        stage.show();
    }

}
