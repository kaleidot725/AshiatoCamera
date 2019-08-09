package kaleidot725.highestpeaks.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.highestpeaks.di.data.Style

class StyleRepositoryImpl() : StyleRepository {
    private val list : ArrayList<Style> = ObservableArrayList<Style>()

    init {
        list.add(Style(16f, "16"))
        list.add(Style(20f, "20"))
        list.add(Style(24f, "24"))
        list.add(Style(28f, "28"))
        list.add(Style(32f, "32"))
        list.add(Style(36f, "36"))
        list.add(Style(40f, "40"))
        list.add(Style(44f, "44"))
        list.add(Style(48f, "48"))
        list.add(Style(52f, "52"))
        list.add(Style(56f, "56"))
        list.add(Style(60f, "60"))
        list.add(Style(64f, "64"))
        list.add(Style(68f, "68"))
        list.add(Style(72f, "72"))
        list.add(Style(76f, "76"))
        list.add(Style(80f, "80"))
        list.add(Style(84f, "84"))
        list.add(Style(88f, "88"))
        list.add(Style(92f, "92"))
        list.add(Style(96f, "96"))
        list.add(Style(100f, "100"))
    }

    override fun all(): List<Style> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}