package com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable;

import com.lzsoft.lzdata.mobile.purex.R;

import java.util.Arrays;
import java.util.List;

public class GenreComponentFactory {

  public static List<GenreComponent> makeGenreComponents() {
    return Arrays.asList(
            makeRecycleViewGenreComponent(),
            makeCardViewGenreComponent(),
            makeListViewGenreComponent(),
            makeTabsGenreComponent(),
            makeNavigationGenreComponent(),
            makePagingGenreComponent(),
            makeSQLiteGenreComponent(),
            makeMySQLGenreComponent(),
            makeArchitectureGenreComponent(),
            makeAnimationGenreComponent(),
            makeChartGenreComponent());
  }

/*
  public static List<MultiCheckGenre> makeMultiCheckGenresComponent() {
    return Arrays.asList(makeMultiCheckRockGenre(),
        makeMultiCheckJazzGenre(),
        makeMultiCheckClassicGenre(),
        makeMultiCheckSalsaGenre(),
        makeMulitCheckBluegrassGenre());
  }
*/

/*
  public static List<SingleCheckGenre> makeSingleCheckGenresComponent() {
    return Arrays.asList(makeSingleCheckRockGenre(),
        makeSingleCheckJazzGenre(),
        makeSingleCheckClassicGenre(),
        makeSingleCheckSalsaGenre(),
        makeSingleCheckBluegrassGenre());
  }
*/

  public static GenreComponent makeRecycleViewGenreComponent() {
    return new GenreComponent("RecycleView Demo", makeRecycleViewComponentSamples(), R.drawable.ic_electric_guitar);
  }

/*
  public static MultiCheckGenre makeMultiCheckRockGenreComponent() {
    return new MultiCheckGenre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }
*/

/*
  public static SingleCheckGenre makeSingleCheckRockGenreComponent() {
    return new SingleCheckGenre("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }
*/

  public static List<ComponentSample> makeRecycleViewComponentSamples() {
    ComponentSample thoughtbotRecyclerviewExapend = new ComponentSample("ThoughtbotExapendRecyclerview","来源：这是Thoughtbot源码演示", "com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview",true);
    ComponentSample fullRecyclerviewExapend = new ComponentSample("FullExapendRecyclerview","来源：Full Recyclerview","com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview", true);
    ComponentSample expandableControPanel = new ComponentSample("ExpandableControPanel", "来源：","com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable",true);
    ComponentSample enteranimRecyclerView = new ComponentSample("EnterAnimationRecyclerView","来源：" ,"com.patrickiv.demo.enteranimationdemo",true);
    ComponentSample fullRecyclerViewSnap = new ComponentSample("Full Recyclerview Snap Demo", "来源：FullRecyclerView","com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.snap",true);
    ComponentSample fulRecyclerViewlUpdateData = new ComponentSample("Full Recyclerview Update Data Demo", "来源：FullRecyclerView","com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.updateData",true);

    return Arrays.asList(thoughtbotRecyclerviewExapend, fullRecyclerviewExapend, expandableControPanel, enteranimRecyclerView, fullRecyclerViewSnap, fulRecyclerViewlUpdateData);
  }

  public static GenreComponent makeCardViewGenreComponent() {
    return new GenreComponent("CardView Demo", makeCardViewComponentSamples(), R.drawable.ic_electric_guitar);
  }

  public static List<ComponentSample> makeCardViewComponentSamples() {
    ComponentSample albumsCardViewDemo = new ComponentSample("Albums CardView Demo","来源：Albums CardView 演示", "com.lzsoft.lzdata.mobile.purex.componentlib.CardView.albumcardviewdemo",true);

    return Arrays.asList(albumsCardViewDemo);
  }

  public static GenreComponent makeListViewGenreComponent() {
    return new GenreComponent("ListView Demo", makeListViewComponentSamples(), R.drawable.ic_electric_guitar);
  }

  public static List<ComponentSample> makeListViewComponentSamples() {
    ComponentSample recipesListViewDemo = new ComponentSample("Recipes ListView Demo","来源：Recipes ListView 演示", "com.lzsoft.lzdata.mobile.purex.componentlib.ListView.Recipes",true);

    ComponentSample etsySampleActivityViewDemo = new ComponentSample("Etsy Staggered Grid View Demo","来源：Android Staggered Grid View 演示", "com.example.etsy",true);

    return Arrays.asList(recipesListViewDemo, etsySampleActivityViewDemo);
  }

  public static GenreComponent makeTabsGenreComponent() {
    return new GenreComponent("Tabs Demo", makeTabsComponentSamples(), R.drawable.ic_electric_guitar);
  }

  public static List<ComponentSample> makeTabsComponentSamples() {
    ComponentSample materialDesignTabs = new ComponentSample("Material Design Tabs", true);
    ComponentSample moviesTabsSwipeTab = new ComponentSample("Movies Tabs Swipe Demo", true);

    ComponentSample navigationSampleDemo = new ComponentSample("Inazaruk Navigation Demo", true);
    ComponentSample actionsContentViewDemo = new ComponentSample("Inazaruk  ActionsContentView Demo", true);

    ComponentSample ultimateTabLayoutDemo = new ComponentSample("UltimateTabLayout for ViewPager", "源：ATHBK/UltimateTabLayout","com.example.athbk.ultimatetablayout",true);

    return Arrays.asList(materialDesignTabs, moviesTabsSwipeTab, actionsContentViewDemo, navigationSampleDemo, ultimateTabLayoutDemo);
  }

  public static GenreComponent makeNavigationGenreComponent() {
    return new GenreComponent("Navigation Demo", makeNavigationComponentSamples(), R.drawable.ic_electric_guitar);
  }

  public static List<ComponentSample> makeNavigationComponentSamples() {
    ComponentSample materialDesignTabs = new ComponentSample("Android Navigation Basic Demo", true);
    ComponentSample navigationSampleDemo = new ComponentSample("NavigationSample Demo", true);
    ComponentSample actionsContentViewDemo = new ComponentSample("ActionsContentView Demo", true);

    return Arrays.asList(materialDesignTabs, actionsContentViewDemo, navigationSampleDemo);
  }

  public static GenreComponent makePagingGenreComponent() {
    return new GenreComponent("Paging Demo", makePagingComponentSamples(), R.drawable.ic_electric_guitar);
  }

  public static List<ComponentSample> makePagingComponentSamples() {
    ComponentSample pagingSampleDemo = new ComponentSample("Android Paging Sample", true);

    return Arrays.asList(pagingSampleDemo);
  }

  public static GenreComponent makeSQLiteGenreComponent() {
    return new GenreComponent("SQLite Demo", makeSQLiteComponentSamples(), R.drawable.ic_maracas);
  }

  public static List<ComponentSample> makeSQLiteComponentSamples() {
    ComponentSample androidBasicDemoPesistence = new ComponentSample("Basic Sample Pesistence","SQLite Room Demo", "com.example.android.persistence",true);

    return Arrays.asList(androidBasicDemoPesistence);
  }


  public static GenreComponent makeMySQLGenreComponent() {
    return new GenreComponent("MySQL Demo", makeMySQLComponentSamples(), R.drawable.ic_banjo);
  }

/*
  public static MultiCheckGenre makeMulitCheckBluegrassGenre() {
    return new MultiCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }
*/

/*
  public static SingleCheckGenre makeSingleCheckBluegrassGenre() {
    return new SingleCheckGenre("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }
*/

  public static List<ComponentSample> makeMySQLComponentSamples() {
    ComponentSample androidMySQLDataPure = new ComponentSample("MySQL Data Pure Demo","MySQL Data PHP Demo", "com.lzsoft.lzdata.mobile.purex.database.mysql.puredata",true);
    ComponentSample androidMySQLDbConn = new ComponentSample("Ravi MySQL Conn Demo","MySQL Data Connection Demo", "com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn",true);
    ComponentSample androidRemoteMySQLDbConn = new ComponentSample("Remote MySQL DB Demo","Remote MySQL DB Demo", "com.lzsoft.lzdata.mobile.purex.database.mysql.ravi.dbconn",true);


    return Arrays.asList(androidMySQLDataPure, androidMySQLDbConn, androidRemoteMySQLDbConn);
  }




  public static GenreComponent makeArchitectureGenreComponent() {
    return new GenreComponent("Architecture Components Demo", makeArchitectureComponentSamples(), R.drawable.ic_maracas);
  }

  public static List<ComponentSample> makeArchitectureComponentSamples() {
    ComponentSample androidBasicDemoPesistence = new ComponentSample("SQLite Room Sample","SQLite Room(Architecture Component) Demo", "com.example.android.persistence",true);
    ComponentSample androidRxJavaDemo = new ComponentSample("Basic RxJava Sample","Basic RxJava(Architecture Component) Demo", "com.example.android.observability",true);

    return Arrays.asList(androidBasicDemoPesistence, androidRxJavaDemo);
  }


  public static GenreComponent makeAnimationGenreComponent() {
    return new GenreComponent("Animation Demo", makeAnimationComponentSamples(), R.drawable.ic_saxaphone);
  }

/*
  public static MultiCheckGenre makeMultiCheckJazzGenre() {
    return new MultiCheckGenre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }
*/

/*
  public static SingleCheckGenre makeSingleCheckJazzGenre() {
    return new SingleCheckGenre("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }
*/

  public static List<ComponentSample> makeAnimationComponentSamples() {
    ComponentSample continuousAnimDemo = new ComponentSample("ContinuousAnimDemo", true);
    ComponentSample daimajiaAnimationDemo = new ComponentSample("Daimajia Animation Demo", "来源：Daimajia动画演示","com.example.daimajia.animationdemo",true);
    ComponentSample daimajiaAnimationExample = new ComponentSample("Daimajia Animation Example", "来源：Daimajia动画演示","com.example.daimajia.animationdemo",true);
    ComponentSample androidIO2014Demo = new ComponentSample("Android IO 2014 Demo", "来源：Android IO 2014 演示","com.example.android.io2014",true);

    ComponentSample rippleEffectUsageDemo = new ComponentSample("Ripple Effect Usage Demo", "来源：https://github.com/waynechen323/RippleEffectUsage","com.waynechen.rippleeffectusage",true);
    ComponentSample rippleDrawableSampleDemo = new ComponentSample("Ripple Drawable Sample", "来源：https://github.com/ozodrukh/RippleDrawable","com.example.ozodrukh.dreamers.sample",true);

    ComponentSample materialRippleDemoDemo = new ComponentSample("Material Ripple Demo", "来源：https://github.com/balysv/material-ripple","com.example.balysv.materialripple.demo",true);
    ComponentSample materialRippleListViewDemoDemo = new ComponentSample("Material Ripple ListView Demo", "来源：https://github.com/balysv/material-ripple","com.example.balysv.materialripple.demo",true);
    ComponentSample materialRippleRecyclerViewDemoDemo = new ComponentSample("Material Ripple RecyclerView Demo", "来源：https://github.com/balysv/material-ripple","com.example.balysv.materialripple.demo",true);

    return Arrays.asList(continuousAnimDemo, daimajiaAnimationDemo, daimajiaAnimationExample, androidIO2014Demo, rippleEffectUsageDemo,
                         rippleDrawableSampleDemo, materialRippleDemoDemo, materialRippleListViewDemoDemo, materialRippleRecyclerViewDemoDemo);
  }

  public static GenreComponent makeChartGenreComponent() {
    return new GenreComponent("Chart", makeChartComponentSamples(), R.drawable.ic_violin);
  }

/*
  public static MultiCheckGenre makeMultiCheckClassicGenre() {
    return new MultiCheckGenre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }
*/

/*
  public static SingleCheckGenre makeSingleCheckClassicGenre() {
    return new SingleCheckGenre("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }
*/

  public static List<ComponentSample> makeChartComponentSamples() {
    ComponentSample mpchartExampleDemo = new ComponentSample("MPChartExampleDemo","这是一个图形表的演示", false);


    return Arrays.asList(mpchartExampleDemo);
  }

}

