
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.RequestsView

@Composable
@Preview
fun App() {
    RequestsView().view()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
