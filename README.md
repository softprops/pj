# pj

Makes json all pretty-like.

## as a conscript app

### install

Installation requires [conscript][cs]

    cs softprops/pj
    
### usage

Formatting piped data with curl (discarding stderr), use empty `--` flag

    curl 'http://api.tea.io/time' 2>/dev/null | pj --
    {
      "tea_time": true
    }
    
Formatting json file and writing to anoter file.

    pj -f path/to/in.json -o path/to/out.json
    
Formatting inline json

    pj -j '{"oh":"la,la","datas":[1,2,3,4],"objects":{"waka":"waka"}}'
    
Getting help

    pj -h

## as a library

### install

Install using [ls][ls]

    ls-install pj

Install by hand

    libraryDependencies += "me.lessis" %% "pj" % "0.1.0"

### usage

    val raw = """{"oh":"la,la","datas":[1,2,3,4],"objects":{"waka":"waka"}}"""
    println(pj.Printer(raw))
    {
      "oh" : "la,la",
      "datas" : [ 1, 2, 3, 4 ],
      "objects" : {
        "waka" : "waka"
      }
    }
    

Doug Tangren (softprops) 2012

[cs]: https://github.com/n8han/conscript#readme
[ls]: http://ls.implicit.ly/

