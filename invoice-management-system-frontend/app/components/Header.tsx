import React from "react";
import Image from "next/image";

export default function Header() {
  return (
    <div>
      <header className="hearder">
        <div className="flex justify-center">
          <Image
            className="logo h-[60px] w-[60px] mt-4"
            src="/favicon.ico"
            alt="RS"
            width="100"
            height="100"
          />
          <p className="text-[60px]">RS</p>
        </div>
        <div className="ml-6 text-2xl">Invoice List</div>
      </header>
    </div>
  );
}
