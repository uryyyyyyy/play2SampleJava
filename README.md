# play2Sample

## 目的

JavaでWebサーバーを書くためのひな形を用意する。

## アーキテクチャ

- とにかく副作用を局所化する。
- 設定（application.conf）で実装を差し替える。
- 異常系は例外を投げる。（JavaでEitherとか嫌だから。）
- lombok好き。

## Model

NoSQLがどこで使えるか、差し替え可能に書けるかを試す。

今回の実装ではRDB（H2-mysqlMode）とKVS（DynamoDB）で試す。
理由は準備が楽だから。

### Model1

- 単独データパターン
- 単純なKey-Valueのペア
- ex: tag 

### Model2

- 一対多パターン
- 子が親を知っているデータ構造
- ex: articleとcomment

### Model3

- 多対多パターン
- それぞれ別のデータが連携し合う場合
- ex: articleとtag

### Model4

- ロック不要パターン
- 一つの値を複数人が更新するが、制約はないので操作履歴を持っておけば良い。
- ex: いいねボタン

### Model5

- ロック必要パターン
- 現在の状態からの相対変化が発生する、かつ制限が存在する場合
- ex: 口座残高（マイナスになってはいけない）

### Model6

- Transactionパターン
- 複数のデータを同時に操作する必要がある場合
- ex: 送金（同種データ）・モノの売買（異種データ）
- 相対的な変化を持つ場合はロックも必要。

### Model7

- 複数DBパターン
- 異なるDBを単一の処理で更新する場合
- ex: S3のようなファイルサーバ（ファイラ＆DB）・TATMの引き出し（口座残高とお金の出力）

### 考察

そもそもRDBがスケールしない（複数マシンに分散できない）箇所はどこか。

- 複数ノードでの読み込みのスケールアウト
  - 非同期Replicationで対応可能

- 異種データ間のスケールアウト
  - 管理主体が分かれると、データをまたいだトランザクションが効かなくなる。
  - スキーマを分けることで対応可能

- 同種データ間のスケールアウト
  - 管理主体が分かれると、データをまたいだトランザクションが効かなくなる。
  - シャーディングで対応可能

- 同データのスケールアウト
  - 管理主体が分かれると、排他制御ができなくなる。
  - RDBでこれを許容することはない？
  - 書き込みを分散することで、NoSQLが最もメリットを出せるであろう箇所。

- 物理的に離れている場合の書き込みのスケールアウト
  - データ整合性をとるには一箇所で制御する必要があるため、整合性の確保は不可能。
  - がんばるなら同期Replication
  - 整合性が不要なデータならシャーディングetc...

## クライアントサイド

### SinglePageApplication

#### メリット

- サーバー・クライアント間はJsonをやりとりするだけなので粗結合になる。（別々に開発できる。）
- サーバーサイドのテンプレートエンジンに依存しない。
- ajaxを駆使することで高速に動作する。
- 通信を小分けにできるので処理がシンプルになる。
- 通信量が減る。
- キャッシュをきかせやすい。


#### デメリット

- 一般的にSEOに弱い。
- 古いブラウザ・低スペックマシンに対応するのが難しい。
- ルーティングを上手くやらないと、リンクから飛ぶ使い方がしにくい。
- 初回読み込みが遅い
