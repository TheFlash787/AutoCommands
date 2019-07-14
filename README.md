# AutoCommands
Inspired by an idea from **NLBLacky**

### What is it?
Do you need to run specific commands on startup? Or perhaps are you wanting to run a command X minutes after startup? Very possibly, you're trying to run a command every couple minutes? This plugin can do exactly that!

**AutoCommands** allows you to run commands on startup, on startup *with a delay*, or from startup on a particular interval. Everything is configurable and the different modules can be enabled/disabled with ease.

### How do I use it?
1. Drop the plugin jar file into your `/mods` or `/plugins` folder.
2. Launch your server and that's it!

### configuration
**By default, all modules are disabled so that you don't send any blank commands. Feel free to preload your configuration in the file** `/autocommands/autocommands.conf`
```conf
delay {
    # This module will run the specified commands after the specified delay in minutes
    enabled=false
    # Specify how many minutes the server should wait before executing the commands
    delay-in-minutes=2
    # The commands will be run after the delay
    delayed-commands=[
      "",
      ""
    ]
}
interval {
    # This module will run the specified commands at every interval that you have specified below in minutes
    enabled=false
    # The commands will be run at each interval
    interval-commands=[
      "",
      ""
    ]
    # Specify how often the server should run these commands in minutes
    interval-in-minutes=1
}
startup {
    # This module will run the specified commands on startup
    enabled=false
    # The commands specified will be run when the server has started up.
    startup-commands=[
      "",
      ""
    ]
}
```

### Support
If you ever need support with regards to our plugin(s), please don't hesitate to contact us directly on our community Discord server. You can find it here: https://discord.gg/tKKeTdc


![](https://storage.modrealms.net/mobpression/cow.png)
