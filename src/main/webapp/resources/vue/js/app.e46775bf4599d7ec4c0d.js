webpackJsonp([2],{"6JGt":function(e,t){},Htri:function(e,t){},NHnr:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=n("7+uW"),r={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("a",{attrs:{href:"move.vue"}},[e._v("뷰->메인")]),e._v(" "),n("div",{attrs:{id:"nav"}},[n("router-link",{attrs:{to:"/indexVue.vue"}},[e._v("Home")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/about.vue"}},[e._v("About")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/spring.vue"}},[e._v("Spring")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/register.vue"}},[e._v("Register")]),e._v(" |\n    "),n("router-link",{attrs:{to:"/findid.vue"}},[e._v("FindId")])],1),e._v(" "),n("router-view")],1)},staticRenderFns:[]};var u=n("VU/8")(null,r,!1,function(e){n("Htri")},null,null).exports,i=n("/ocq"),a=n("RW8i"),s={props:{value:{type:String,required:!0}},methods:{}},d={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("label",{attrs:{for:""}},[e._v("Name")]),e._v(" "),n("input",{staticStyle:{padding:"30px",border:"2px solid green"},attrs:{type:"text"},domProps:{value:e.value},on:{input:function(t){return e.$emit("input",t.target.value)}}})])},staticRenderFns:[]};var c=n("VU/8")(s,d,!1,function(e){n("dteV")},null,null).exports,l={components:{KossieCoder:a.a,InputField:c},data:function(){return{name:"Kossie Coder"}},beforeCreate:function(){},created:function(){},beforeMount:function(){},mounted:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){},methods:{updateName:function(){this.name="hello"}}},v={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"home"},[n("h1",[e._v("This is Home page")]),e._v(" "),n("KossieCoder",{attrs:{title:"home title",name:"Kossie Coder"}}),e._v(" "),n("form",{attrs:{action:""}},[n("InputField",{model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),e._v(" "),n("br"),n("button",{on:{click:e.updateName}},[e._v("Submit")]),e._v(" "),n("button",[e._v("Submit")])],1),e._v("\n\t"+e._s(e.name)+"\n")],1)},staticRenderFns:[]};var f=n("VU/8")(l,v,!1,function(e){n("WvfV")},"data-v-6ef00afd",null).exports;o.a.use(i.a);var p=[{path:"/indexVue.vue",name:"Home",component:f},{path:"/about.vue",name:"About",component:function(){return n.e(0).then(n.bind(null,"vu9I"))}},{path:"/spring.vue",name:"Spring",component:function(){return n.e(0).then(n.bind(null,"upH3"))}},{path:"/register.vue",name:"Register",component:function(){return n.e(0).then(n.bind(null,"tcoL"))}},{path:"/findid.vue",name:"Findid",component:function(){return n.e(0).then(n.bind(null,"4fEu"))}}],m=new i.a({mode:"history",base:Object({NODE_ENV:"production"}).BASE_URL,routes:p}),_=n("NYxO"),h=n("mtWM"),g=n.n(h),b={namespaced:!0,state:{users:{username:"",password:""},username:""},mutations:{SET_USERS:function(e,t){e.users=t},FIND_ID:function(e,t){console.log("gd: "+t),e.username=t,console.log("sss : "+e.username)}},actions:{getId:function(e,t){var n=e.commit;console.log("kwc : "+t),g.a.post("findId.vue",{username:t}).then(function(e){console.log(e),n("FIND_ID",e.data)})},getUsers:function(e,t){e.commit;console.log("gd : "+t),g.a.post("join.vue",t).then(function(e){console.log(e)})}}};o.a.use(_.a);var S=new _.a.Store({modules:{user:b}});o.a.config.productionTip=!1,new o.a({router:m,store:S,render:function(e){return e(u)}}).$mount("#app")},RW8i:function(e,t,n){"use strict";var o={props:{title:{type:String,required:!1,default:"default title"},name:{type:String,default:"default name"}},data:function(){return{kossie:"coder"}},methods:{updateName:function(){this.name="Kossie Coder Updated"}}},r={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("p",[e._v("header")]),e._v(" "),e._t("header",null,{kossie:e.kossie}),e._v(" "),n("p",[e._v("Body")]),e._v(" "),e._t("footer"),e._v(" "),n("p",[e._v("footer")]),e._v(" "),e._t("default")],2)},staticRenderFns:[]};var u=n("VU/8")(o,r,!1,function(e){n("6JGt")},"data-v-5e48ac50",null);t.a=u.exports},WvfV:function(e,t){},dteV:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.e46775bf4599d7ec4c0d.js.map