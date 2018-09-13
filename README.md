# DS-text-search (ver. 2) indexer

GFFファイルの検索とJBrowseとの連携
Apache LuceneのRegexpQueryを使った正規表現による全文検索

これを行うためのindexerプログラムである。


概要

Apache Luceneは全文検索のためのJavaのライブラリであり、
ElasticSearchやApache Solrといった有名なオープンソースの全文検索システムは
内部でApache Luceneを使っている。

Luceneの普通の使い方はStandardAnalyzerなどで字句解析して転置インデックスを作ることであるが、
この方法だと後方一致ができない。(例えば正規表現なら.?RNAとやればmRNA, rRNA, ...を探せるがこれができない。)
このためLuceneのRegexQueryを用いて正規表現による全文検索とした。

単純にJavaの正規表現ルーチンを使った場合はWebアプリの処理速度として実用にならなかったが、
本バージョンでは13万行程度のgffファイルでも十分高速に動作する。


## 更新履歴

- version 2.0.2
    - インデックス作成時にデバッグに役立つ情報を出力するようにした
- version 2.0.0
    - 基本的な機能を実装
      - Apache LuceneのRegexpQueryによる全文検索


## インストール方法

### 前提

- Java version 10
- Apache maven version 3
- git 


### コンパイル方法

    git clone http://gitlab.ddbj.nig.ac.jp/oogasawa/ds-text-search2-indexer.git
    cd ds-text-search2-indexer
    git checkout v2.0.2
    mvn -Dmaven.test.skip=true clean package
    
これによりtargetディレクトリの下にjarファイルが生成される。


## 起動方法


    java --illegal-access=deny \
        -jar target/ds-text-search2-indexer-2.0.2.jar \
        --datafile=/home/you/your-data.gff
        --index=/home/you/genome.index

11MB程度のファイルなら1分もかからない程度でインデックスができる。

