package kaleidot725.ashiato.di.repository

import androidx.databinding.ObservableArrayList
import kaleidot725.ashiato.di.data.Style

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
        list.add(Style(104f, "104"))
        list.add(Style(108f, "108"))
        list.add(Style(112f, "112"))
        list.add(Style(116f, "116"))
        list.add(Style(120f, "120"))
        list.add(Style(124f, "124"))
        list.add(Style(128f, "128"))
        list.add(Style(132f, "132"))
        list.add(Style(136f, "136"))
        list.add(Style(140f, "140"))
        list.add(Style(144f, "144"))
        list.add(Style(148f, "148"))
        list.add(Style(152f, "152"))
        list.add(Style(156f, "156"))
        list.add(Style(160f, "160"))
        list.add(Style(164f, "164"))
        list.add(Style(168f, "168"))
        list.add(Style(172f, "172"))
        list.add(Style(176f, "176"))
        list.add(Style(180f, "180"))
        list.add(Style(184f, "184"))
        list.add(Style(188f, "188"))
        list.add(Style(192f, "192"))
        list.add(Style(196f, "196"))
        list.add(Style(200f, "200"))
    }

    override fun all(): List<Style> {
        return list.toList()
    }

    override fun count(): Int {
        return list.count()
    }
}