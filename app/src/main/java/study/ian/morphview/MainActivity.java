package study.ian.morphview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import study.ian.morphviewlib.MorphView;

public class MainActivity extends AppCompatActivity {

    private MorphView morphView;
    private boolean isMorph = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViewPath();

        morphView.setOnClickListener(v -> {
            if (isMorph) {
                morphView.performAnimation(R.drawable.vd_loading_sin_2);
                isMorph = false;
            } else {
                morphView.performAnimation(R.drawable.vd_loading_sin_1);
                isMorph = true;
            }
        });
    }

    private void findViews() {
        morphView = findViewById(R.id.morphView);
    }

    private void initViewPath() {
        morphView.setCurrentId(R.drawable.vd_loading_sin_1);
        morphView.performAnimation(R.drawable.vd_loading_sin_1);
        morphView.setSize(1080, 1920);
        morphView.setPaintColor(0xff000000);
    }
}
