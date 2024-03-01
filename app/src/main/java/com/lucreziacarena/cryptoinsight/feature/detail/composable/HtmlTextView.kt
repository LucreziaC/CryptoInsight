package com.lucreziacarena.cryptoinsight.feature.detail.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun HtmlTextView(htmlText: String, onClickLink: (String) -> Unit) {
    val annotatedString = buildAnnotatedString {
        appendHtml(htmlText, onClickLink)
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()
                ?.let { annotation ->
                    if (annotation.tag == "URL") {
                        onClickLink(annotation.item)
                    }
                }
        }
    )
}

fun AnnotatedString.Builder.appendHtml(html: String, onClickLink: (String) -> Unit) {
    val regex = Regex("""<a href="([^"]+)">([^<]+)</a>""")
    var lastIndex = 0
    regex.findAll(html).forEach { result ->
        val (url, text) = result.destructured
        append(html.substring(lastIndex, result.range.start))
        val start = length
        append(text)
        val end = length
        addStringAnnotation("URL", url, start, end)
        addStyle(
            SpanStyle(textDecoration = TextDecoration.Underline),
            start,
            end
        )
        lastIndex = result.range.endInclusive + 1
    }
    append(html.substring(lastIndex))
}
