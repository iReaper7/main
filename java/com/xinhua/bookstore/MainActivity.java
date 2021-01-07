package com.xinhua.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinhua.bookstore.Table.BC;
import com.xinhua.bookstore.Table.Book;
import com.xinhua.bookstore.Table.Category;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private List<Book> bookList;
    private RecyclerView recyclerView;

    public void init() {
        Connector.getDatabase();
        bookList = DataSupport.findAll(Book.class);
        //创建布局管理，加载RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //增加数据库数据，进行一次测试
    public void test() {
        DataSupport.deleteAll(Book.class);
        new Book(R.drawable.bookphoto2, "食品安全危机信息在社交媒体中的传播研究", "韩太平" ,"中国社会科学出版社",
                 45.60).save();
    }
    //增加数据库数据
    public void addCategory() {
        DataSupport.deleteAll(Category.class);
        new Category("小说").save();
        new Category("儿童读物").save();
        new Category("专业书").save();
        new Category("工具书").save();
        new Category("手册").save();
        new Category("书目").save();
        new Category("剧本").save();
        new Category("日记").save();
    }

    public void showImageId() {
        Log.d("MainActivity", String.valueOf(R.drawable.book_image1));
        Log.d("MainActivity", String.valueOf(R.drawable.book_image2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test();
        //addCategory();
        init();
        showImageId();
    }

    @Override
    protected void onResume () {
        super.onResume();
        bookList = DataSupport.findAll(Book.class);
        recyclerView.setAdapter(new BookAdapter(bookList));
        //新增按钮监听器
        Button addStudent = findViewById(R.id.add_book_button);
        addStudent.setOnClickListener(v -> {
            startActivity(new Intent(this, AddBook.class));
        });
    }

}