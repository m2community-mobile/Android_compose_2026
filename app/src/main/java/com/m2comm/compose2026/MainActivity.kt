package com.m2comm.compose2026


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.m2comm.compose2026.ui.UrlData
import com.m2comm.compose2026.ui.WebDetailScreen
import com.m2comm.compose2026.ui.theme.Compose2026Theme

import androidx.compose.runtime.getValue // 'by' 키워드를 위해 필수
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.m2comm.compose2026.ui.theme.NoticePink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT, // 배경은 투명하게
                android.graphics.Color.TRANSPARENT  // 스크림(그림자) 없음
            )
        )
        setContent {
            Compose2026Theme {
                MainScreen() // 메인 스크린 컴포저블 호출
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 1. 드로어 상태 및 코루틴 스코프 준비
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. 최상위에 ModalNavigationDrawer를 배치 (화면 전체 높이 점유)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // 드로어 내용 (전체 높이로 나타남)
            ModalDrawerSheet {
                Text("메뉴 항목 1", modifier = Modifier.padding(16.dp))
                Text("메뉴 항목 2", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        // 3. 드로어 내부에 Scaffold 배치
        Scaffold(
            bottomBar = {
                // 특정 경로에서만 하단바 표시 로직 유지
                if (currentRoute != "web") {
                    BottomFooter(navController)
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (currentRoute != "web") innerPadding else PaddingValues(0.dp))
            ) {
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        // 중요: LayoutExample12에 drawerState와 scope를 전달하여
                        // 상단 버튼 클릭 시 드로어를 열 수 있게 합니다.
                        LayoutExample12(navController, drawerState, scope)
                    }
                    composable("detail") { DetailScreen(navController) }
                    composable("web") { WebDetailScreen(navController, UrlData.PROGRAM02) }
                }
            }
        }
    }
}

@Composable
fun LayoutExample12(
    navController: NavController,
    drawerState: DrawerState,      // 추가
    scope: CoroutineScope          // 추가
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .systemBarsPadding()) {
        Image(
            painter = painterResource(id = R.drawable.ic_menu_main),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .clickable {
                    // 전달받은 스코프와 상태를 사용하여 드로어 열기
                    scope.launch {
                        drawerState.open()
                    }
                }
        )

        Spacer(modifier = Modifier.weight(1f))

        data class MenuData(
            val title: String,
            val iconRes: Int
        )
        val navigationMenus = listOf(
            MenuData("인사말", R.drawable.img_menu01),
            MenuData("일정", R.drawable.img_menu02),
            MenuData("초록보기", R.drawable.img_menu03),
            MenuData("E-poster", R.drawable.img_menu04),
            MenuData("Highlight", R.drawable.img_menu05),
            MenuData("피드백", R.drawable.img_menu06),
            MenuData("대회장 안내", R.drawable.img_menu07),
            MenuData("전시 안내", R.drawable.img_menu08),
            MenuData("포토갤러리", R.drawable.img_menu09)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(), // 남은 화면 공간을 다 차지함
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(9) { index ->
                // 1. 정보를 묶을 클래스 정의
                GridItemSetBig(navigationMenus[index].title, navigationMenus[index].iconRes)
            }
        }

        // 2. 중간 배경 이미지 (핵심 영역)
        // weight(1f)를 주어 상단과 하단 고정 영역을 제외한 남은 공간을 다 차지함
        Image(
            painter = painterResource(id = R.drawable.img_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop, // 영역을 꽉 채우도록 설정
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // 3. 하단 블랙 박스 (고정 80dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // 높이 50 유지
                .background(NoticePink),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), // 양옆 여백 (필요시 조절)
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. 왼쪽 시작 이미지 (20dp 고정)
                Image(
                    painter = painterResource(id = R.drawable.ic_notice), // 아이콘 리소스
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                // 2. 나머지 공간을 채우는 텍스트 영역
                Box(
                    modifier = Modifier
                        .weight(1f) // 이미지 제외 남은 공간 다 차지
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center // 남은 공간 내에서 텍스트 중앙 정렬
                ) {
                    Text(
                        text = "하단 고정 텍스트 1",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(
                            Font(R.font.sc_mid, FontWeight.Normal),
                            Font(R.font.sc_bold, FontWeight.Bold)
                        ),
                        color = Color.White
                    )
                }

                // (선택) 오른쪽 대칭을 맞추고 싶다면 20.dp의 빈 Spacer를 넣기도 합니다.
                // Spacer(modifier = Modifier.size(20.dp))
            }
        }

    }
}


@Composable
fun DetailScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "새로운 화면입니다!")
            Button(onClick = { navController.popBackStack() }) { // 뒤로가기
                Text("돌아가기")
            }
        }
    }
}


@Composable
fun BottomFooter(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .navigationBarsPadding()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically // 세로 중앙 정렬
    ) {

        // 1. 정보를 묶을 클래스 정의
        data class MenuData(
            val title: String,
            val iconRes: Int
        )
        // 2. 리스트 합치기
        val navigationMenus = listOf(
            MenuData("홈", R.drawable.img_bottom_01),
            MenuData("프로그램", R.drawable.img_bottom_02),
            MenuData("진행세션", R.drawable.img_bottom_03),
            MenuData("검색", R.drawable.img_bottom_04),
            MenuData("즐겨찾기", R.drawable.img_bottom_05)
        )
        navigationMenus.forEach { title ->
            Box(
                modifier = Modifier
                    .weight(1f) // 가로 공간을 1/5씩 정확히 배분
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center // 텍스트를 각 칸의 중앙에 배치
            ) {
                GridItemSet(title.title, title.iconRes)
            }
        }
    }
}


@Composable
fun LayoutExample() {
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. 화면 전체를 덮는 이미지 (하단 30dp 제외)
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp) // 하단 텍스트 높이만큼 제외
        )

        // 2. 상단 이미지와 하단 텍스트 배치
        Column(modifier = Modifier.fillMaxSize()) {
            // 상단: 너비 30, 높이 30 (임의 설정) 이미지
            Image(
                painter = painterResource(id = R.drawable.img_top_menu),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )

            // 중간: 남은 공간을 다 차지하여 텍스트를 아래로 밀어냄
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center // 내부 요소(Text)를 정렬
            ) {
                Text(
                    text = "하단 고정 텍스트2",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            // 하단: 높이 30 고정 텍스트
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center // 내부 요소(Text)를 정렬
            ) {
                Text(
                    text = "하단 고정 텍스트2",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun XmlToComposeScreen2() {
    var textInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),// 시스템 상태바 겹침 방지용 (필요시)
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- 추가된 부분: 왼쪽 상단 이미지 버튼 ---
        Box(
            modifier = Modifier
                .fillMaxWidth() // 왼쪽과 위에 마진 10dp
        ) {


            // 기존 ImageView (100dp 높이)
            Image(
                painter = painterResource(id = R.drawable.img_sample),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.img_top_menu),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp) // 30x30 사이즈
                    .clickable {
                        /* 여기에 클릭 이벤트 */
                    }
            )

            Text(
                text = "Bottom of Parent Footer",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // 👈 이것이 Bottom_toBottomOf="parent" 역할
                    .background(Color.Black)
                    .padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )


        }
        // ------------------------------------


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f), // 남은 화면 공간을 다 차지함
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(9) { index ->
                val customTitle = when (index) {
                    0 -> "첫번째"
                    1 -> "두번째"
                    2 -> "세번째"
                    3 -> "네번째"
                    4 -> "다섯번째"
                    5 -> "여섯번째"
                    6 -> "일곱번째"
                    7 -> "여덟번째"
                    8 -> "아홉번째"
                    else -> "기타"
                }
                GridItemSet(title = customTitle, R.drawable.img_sample)
            }
        }
    }
}


@Composable
fun GridItemSetBig(title: String, imgRes:Int) {

    val context = LocalContext.current

    // 1. 클릭 상호작용 및 애니메이션 설정
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f, // 전체가 살짝 눌리는 효과
        label = "ScaleAnimation"
    )

    // LinearLayout(vertical, gravity="center") 대응
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current, // 리플 효과
                onClick = {
                    Toast.makeText(context, "$title 항목 클릭!", Toast.LENGTH_SHORT).show()
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
        verticalArrangement = Arrangement.Center // 세로 중앙 정렬
    ) {
        // ImageView 대응
        Image(
            painter = painterResource(id = imgRes),
            contentDescription = null,
            modifier = Modifier
                .height(60.dp) // height="100dp"
                .aspectRatio(1f), // 이미지 비율 유지 (선택 사항)
            contentScale = ContentScale.Fit // adjustViewBounds와 유사한 효과
        )

        // TextView 대응
        Text(
            text = title,
            fontSize = 15.sp,
            fontFamily = FontFamily(
                Font(R.font.sc_mid, FontWeight.Normal),
                Font(R.font.sc_bold, FontWeight.Bold)
            ),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GridItemSet(title: String, img: Int) {

    val context = LocalContext.current

    // 1. 클릭 상호작용 및 애니메이션 설정
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f, // 전체가 살짝 눌리는 효과
        label = "ScaleAnimation"
    )

    // LinearLayout(vertical, gravity="center") 대응
    Column(
        modifier = Modifier
            .wrapContentSize()
            .graphicsLayer { // <-- 이 부분이 핵심입니다!
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current, // 리플 효과
                onClick = {
                    Toast.makeText(context, "$title 항목 클릭!", Toast.LENGTH_SHORT).show()
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
        verticalArrangement = Arrangement.Center // 세로 중앙 정렬
    ) {
        // ImageView 대응
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .height(24.dp) // height="100dp"
                .aspectRatio(1f), // 이미지 비율 유지 (선택 사항)
            contentScale = ContentScale.Fit // adjustViewBounds와 유사한 효과
        )



        // TextView 대응
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Black,
            fontFamily = FontFamily(
                Font(R.font.sc_mid, FontWeight.Normal),
                Font(R.font.sc_bold, FontWeight.Bold)
            ),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun GridItemSet2(title: String) {
    // 1. 무한 애니메이션 설정
    val infiniteTransition = rememberInfiniteTransition(label = "ColorTransition")

    // 2. 1초(1000ms) 간격으로 노란색 <-> 흰색 반복
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(
            animation = tween(1000), // 1초 동안 변화
            repeatMode = RepeatMode.Reverse // 노란색 찍고 다시 흰색으로 돌아옴
        ),
        label = "TextColor"
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        // 3. Text의 color에 애니메이션 상태 적용
        Text(
            text = title,
            fontSize = 15.sp,
            color = animatedColor, // 여기가 핵심!
            modifier = Modifier
                .padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ClickableText(title: String) {
    val context = LocalContext.current
    // 1. 사용자의 상호작용(누름, 뗌 등)을 감지하는 소스
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // 2. 눌렸을 때의 스케일(크기) 설정 (눌리면 0.95배, 아니면 1배)
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        label = "ScaleAnimation"
    )

    Text(
        text = title,
        fontSize = 15.sp,
        color = Color.White,
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale) // 크기 변화 적용
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current, // 기본 리플 효과 유지
                onClick = {
                    Toast.makeText(context, "$title 버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show()
                }
            )
            .background(Color.Black)
            .padding(10.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TopLeftIcon() {
    val context = LocalContext.current

    // Box를 사용하면 내부 요소를 정교하게 배치하기 좋습니다.
    Box(
        modifier = Modifier
            .fillMaxWidth() // 가로 전체를 채워야 왼쪽 배치가 의미 있음
            .padding(10.dp) // XML의 layout_margin="10dp"와 동일
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sample), // 여기에 아이콘 리소스 ID 입력
            contentDescription = "Close or Menu",
            modifier = Modifier
                .size(30.dp) // width="30dp", height="30dp"
                .clickable {
                    Toast.makeText(context, "아이콘 클릭!", Toast.LENGTH_SHORT).show()
                },
            contentScale = ContentScale.Fit
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationDrawer() {
    // 1. Drawer 상태와 코루틴 스코프 준비
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. ModalNavigationDrawer 배치
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // 여기에 왼쪽 바(Drawer)의 UI를 작성합니다.
            ModalDrawerSheet {
                Text("메뉴 항목 1", modifier = Modifier.padding(16.dp))
                Text("메뉴 항목 2", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        // 3. 메인 화면 콘텐츠
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Drawer 예제") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // 4. 아이콘 클릭 시 Drawer 열기 (코루틴 사용)
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize()) {
                Text("화면 중앙 콘텐츠입니다.")
            }
        }
    }
}