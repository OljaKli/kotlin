// one.C
// LANGUAGE: +CompanionBlocks +CompanionExtensions
// WITH_STDLIB
// LIBRARY_PLATFORMS: JVM
package one

@JvmInline
value class C(val value: String) {
    companion {
        fun describe(): String = "describe"
        fun plain(v: Int): Int = v
        val label: String get() = "label"
        var counter: Int = 0
        internal fun internalFun(): Int = 1
        private fun privateFun(): Int = 2

        // The value class in the signature still mangles the name
        fun of(v: Int): C = C(v.toString())
    }
}

// DECLARATIONS_NO_LIGHT_ELEMENTS: C.class[of]
// LIGHT_ELEMENTS_NO_DECLARATION: C.class[constructor-impl;equals-impl;equals-impl0;hashCode-impl;of-Aykwt80;toString-impl]
