package de.syntaxinstitut.e_sport_news.data.models.suche

data class searchItem(
    //val age_ratings: List<Int>,
   // val aggregated_rating: Double,
    //val aggregated_rating_count: Int,
   // val alternative_names: List<Int>,
   // val bundles: List<Int>,
    //val category: Int,
   // val checksum: String,
    //val collection: Int,
    val cover: Int,
    //val created_at: Int,
    //val external_games: List<Int>,
    //val first_release_date: Int,
   // val franchise: Int,
    //val franchises: List<Int>,
    //val game_engines: List<Int>,
    //val game_modes: List<Int>,
    //val genres: List<Int>,
    val id: Int,
    //val involved_companies: List<Int>,
    val keywords: List<Int>,
    val name: String,
    val parent_game: Int,
    //val platforms: List<Int>,
    val player_perspectives: List<Int>,
    val rating: Double,
    val rating_count: Int,
    val release_dates: List<Int>,
    val screenshots: List<Int>,
    val similar_games: List<Int>,
    val slug: String,
    val status: Int,
    val summary: String,
    val tags: List<Int>,
    val themes: List<Int>,
    val total_rating: Double,
    val total_rating_count: Int,
    val updated_at: Int,
    val url: String,
    val websites: List<Int>
)