import storm

class SplitSentenceBolt(storm.BasicBolt):
    def process(self, tup):
        value = tup.values[0]
        storm.emit([value*100])

SplitSentenceBolt().run()




